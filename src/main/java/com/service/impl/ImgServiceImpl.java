package com.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.common.BaseService.SpringContextUtils;
import com.common.utils.ImgUtil;
import com.mapper.ImgPathMapper;
import com.mapper.ImgTitleMapper;
import com.pojo.AsianhmResource;
import com.pojo.ImgPath;
import com.pojo.ImgTitle;
import com.service.MovieResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImgServiceImpl implements PageProcessor {

    @Value("${upload.root.path}")
    public static String root_fold;

    @Autowired
    private MovieResourcesService movieResourcesService;

    @Autowired
    private ImgPathMapper imgPathMapper;

    @Autowired
    private ImgTitleMapper imgTitleMapper;

    private static int i = 0;
    public static int getI() {
        return i;
    }
    public static void setI(int j) {
        i = j;
    }




    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等.setTimeOut(100000)
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {

        // 部分二：定义如何抽取页面信息，并保存下来

        //图片地址list
        List<String> imgUrlList =   page.getHtml().xpath("*/div[@class='details-content text-justify']//img/@src").all();
        page.putField("imgUrlList",imgUrlList);

        //标题
        String title =   page.getHtml().xpath("*/div[@class='news_details']/h1/html()").toString();
        page.putField("title",title);

        if (imgUrlList.isEmpty() || StringUtils.isEmpty(title)) {
            System.out.println("s==============跳过");
            page.setSkip(true);
        }else {
            System.out.println("imgUrlList=============="+imgUrlList);
            System.out.println("title=============="+title);
            //写入本地
            /*for (String imgUrl : imgUrlList){
                ImgUtil.saveLocal(title,imgUrl);
            }*/
            //查重
            Example example = new Example(ImgTitle.class);
            example.createCriteria().andEqualTo("title",title);

            if (imgTitleMapper == null) {
                imgTitleMapper = (ImgTitleMapper) getApplicationContext().getBean(ImgTitleMapper.class);
            }

            int count = imgTitleMapper.selectCountByExample(example);
            if (count <= 0){
                //保存到数据库
                ImgTitle imgTitle = new ImgTitle();
                imgTitle.setTitle(title);
                imgTitleMapper.insert(imgTitle);
                Long imgId = imgTitle.getId();
                List<ImgPath> pathList = new ArrayList<>();
                for (String imgUrl : imgUrlList){
                    ImgPath imgPath = new ImgPath();
                    imgPath.setImgId(imgId);
                    imgPath.setImgPath(imgUrl);
                    imgPath.setIsDown(0);
                    pathList.add(imgPath);
                }
                if (imgPathMapper == null) {
                    imgPathMapper = (ImgPathMapper) getApplicationContext().getBean(ImgPathMapper.class);
                }
                imgPathMapper.insertList(pathList);
            }
        }

        // 部分三：从页面发现后续的url地址来抓取
        //下一页地址
        /*List<String> afterUrlList =   page.getHtml().xpath("/div[@class='details-page clearfix']/ul[@class='clearfix']/li[@class='col-md-6']/a/@href").all();*/

       /* https://www.886pi.com/html/news/69/23.html*/

        List<String> nextPagegbkList = getPageList();

        if (nextPagegbkList != null){
            System.out.println("---初始化----");
            page.addTargetRequests(nextPagegbkList);
        }else {
            System.out.println("---初始化跳过----");
        }


        List<String> pageList =   page.getHtml().xpath("*//a[@class='video-pic loading']/@href").all();
        //下一页
       /* List<String> nextPagegbkList =   page.getHtml().xpath("//a[@class='next pagegbk']/@href").all();*/
        if (!pageList.isEmpty()){
            page.addTargetRequests(pageList);
        }

        if (pageList.isEmpty()){
            System.out.println("==================结束=============");
        }

    }

    private static List<String> getPageList(){
        if (i == 0){
            List<String> nextPagegbkList = new ArrayList<>();
            for(int i=2;i<23;i++){
                String url = "https://www.886pi.com/html/news/69/" + i + ".html";
                String url2 = "/html/news/69/" + i + ".html";
                nextPagegbkList.add(url);
                nextPagegbkList.add(url2);
            }
            setI(2);
            return nextPagegbkList;
        }else{
            return null;
        }
    }


    public void begin() {

        //设置代理
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("322.454.322.242",3443)
                ,new Proxy("343.122.234.402",2343)
                ,new Proxy("234.432.234.432",2442)
                ,new Proxy("241.122.234.673",9642)
                ,new Proxy("241.122.234.765",4268)
                ,new Proxy("241.435.234.402",2468)
                ,new Proxy("241.354.244.532",2346)
        ));

        Spider.create(new ImgServiceImpl())
                //去重
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.886pi.com/html/news/69/5.html")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }

    public ApplicationContext getApplicationContext() {
        return SpringContextUtils.getApplicationContext();
    }

    @Override
    public Site getSite() {
        return site;
    }

}
