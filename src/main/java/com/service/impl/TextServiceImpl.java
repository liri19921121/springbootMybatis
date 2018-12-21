package com.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.common.BaseService.SpringContextUtils;
import com.mapper.ResourceTextCatalogMapper;
import com.mapper.ResourceTextMapper;
import com.pojo.ResourceText;
import com.pojo.ResourceTextCatalog;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@Service
public class TextServiceImpl implements PageProcessor {

    @Autowired
    private ResourceTextMapper resourceTextMapper;

    @Autowired
    private ResourceTextCatalogMapper resourceTextCatalogMapper;

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        if (resourceTextMapper == null) {
            resourceTextMapper = (ResourceTextMapper) getApplicationContext().getBean(ResourceTextMapper.class);
        }
        if (resourceTextCatalogMapper == null) {
            resourceTextCatalogMapper = (ResourceTextCatalogMapper) getApplicationContext().getBean(ResourceTextCatalogMapper.class);
        }
        //标题
        String title = page.getHtml().xpath("*/div[@class='news_details']/h1[@class='text-overflow']/text()").toString();
        page.putField("title", title);

        //内容
        String content = page.getHtml().xpath("*/div[@class='xs-details-content text-xs']/html()").toString();
        page.putField("content", content);


        //目录
        String mulu = page.getHtml().xpath("*/div[@class='box-title']/h3[@class='m-0']/text()").toString();
        page.putField("mulu", mulu);

        //标题集合
        List<String> titleList = page.getHtml().xpath("*/li[@class='col-md-14 col-sm-16 col-xs-12 clearfix news-box']//a/@title").all();
        page.putField("titleList", titleList);

        if (!StringUtils.isEmpty(mulu) && !titleList.isEmpty()){
            for (String s : titleList){
                Example example = new Example(ResourceTextCatalog.class);
                example.createCriteria().andEqualTo("name",s);
                int count = resourceTextCatalogMapper.selectCountByExample(example);
                if (count <= 0){
                    ResourceTextCatalog resourceTextCatalog = new ResourceTextCatalog();
                    resourceTextCatalog.setName(s);
                    resourceTextCatalog.setType(mulu);
                    resourceTextCatalogMapper.insert(resourceTextCatalog);
                }
            }
        }


        if (!StringUtils.isEmpty(title) && !StringUtils.isEmpty(content)){
            Example example = new Example(ResourceText.class);
            example.createCriteria().andEqualTo("title",title);
            int count = resourceTextMapper.selectCountByExample(example);
            if (count <= 0){
                ResourceText resourceText = new ResourceText();
                resourceText.setContent(content);
                resourceText.setTitle(title);
                //查询
                Example example2 = new Example(ResourceTextCatalog.class);
                example2.createCriteria().andEqualTo("name",title);
                List<ResourceTextCatalog> rtc = resourceTextCatalogMapper.selectByExample(example2);
                if (rtc!=null){
                    resourceText.setCatalogId(rtc.get(0).getId());
                }
                resourceTextMapper.insert(resourceText);
            }
        }


        //大栏目
        List<String> urlList = page.getHtml().xpath("*/div[@class='more pull-right']/a[@class='text-muted']/@href").all();
        //页面连接
        List<String> yemianList = page.getHtml().xpath("*/div[@class='layout-box clearfix']//a/@href").all();
        //下一页
        String nextUrl = page.getHtml().xpath("*/a[@class='next pagegbk']/@href").toString();

        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(urlList);
        page.addTargetRequests(yemianList);
        page.addTargetRequest(nextUrl);
    }

    public void begin() {
        //设置代理
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("324.424.32.24",4242)
                ,new Proxy("24.102.234.102",4256)
                ,new Proxy("103.103.243.103",1246)
                ,new Proxy("104.342.332.104",8643)
                ,new Proxy("344.105.243.102",7568)
                ,new Proxy("244.102.42.102",3567)));

        Spider.create(new TextServiceImpl())
                //去重
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.552en.com/html/news/6/")
                //开启5个线程抓取
                .thread(3)
                //设置代理
                .setDownloader(httpClientDownloader)
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
