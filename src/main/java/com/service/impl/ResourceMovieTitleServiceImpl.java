package com.service.impl;

import com.common.BaseService.SpringContextUtils;
import com.common.constant.MovieResourceType;
import com.mapper.*;
import com.pojo.*;
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
import us.codecraft.webmagic.selector.Html;

import java.util.List;

@Service
public class ResourceMovieTitleServiceImpl implements PageProcessor {

    @Autowired
    private ResourceMovieTitleMapper resourceMovieTitleMapper;

    @Autowired
    private ResourceAsianhmMapper asianhmResourceMapper;

    @Autowired
    private ResourceAsianwmMapper asianwmResourceMapper;

    @Autowired
    private ResourceCartoonMapper cartoonResourceMapper;

    @Autowired
    private ResourceDomesticMapper domesticResourceMapper;

    @Autowired
    private ResourceStarMapper starResourceMapper;

    @Autowired
    private ResourceOtherMapper otherResourceMapper;

    @Autowired
    private ResourceEuropeMapper europeResourceMapper;

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {

        //************************************************封面处理********************************
        //大栏目
        String bigTitle = page.getHtml().xpath("*//li[@class='active']/a[@target='_blank']/html()").toString();
        page.putField("bigTitle", bigTitle);

        //小栏目
        String minTitle = page.getHtml().xpath("*//a[@data='order-addtime']/html()").toString();
        page.putField("minTitle", minTitle);
        //封面
        List<String> titleList = page.getHtml().xpath("*/li[@class='col-md-2 col-sm-3 col-xs-4 ']/a[@class='video-pic loading']").all();
        if (titleList.isEmpty()) {
            page.setSkip(true);
        } else {
            for (String s : titleList) {
                if (resourceMovieTitleMapper == null) {
                    resourceMovieTitleMapper = (ResourceMovieTitleMapper) getApplicationContext().getBean(ResourceMovieTitleMapper.class);
                }

                if (asianhmResourceMapper == null) {
                    asianhmResourceMapper = (ResourceAsianhmMapper) getApplicationContext().getBean(ResourceAsianhmMapper.class);
                }
                if (asianwmResourceMapper == null) {
                    asianwmResourceMapper = (ResourceAsianwmMapper) getApplicationContext().getBean(ResourceAsianwmMapper.class);
                }
                if (cartoonResourceMapper == null) {
                    cartoonResourceMapper = (ResourceCartoonMapper) getApplicationContext().getBean(ResourceCartoonMapper.class);
                }
                if (domesticResourceMapper == null) {
                    domesticResourceMapper = (ResourceDomesticMapper) getApplicationContext().getBean(ResourceDomesticMapper.class);
                }
                if (starResourceMapper == null) {
                    starResourceMapper = (ResourceStarMapper) getApplicationContext().getBean(ResourceStarMapper.class);
                }
                if (otherResourceMapper == null) {
                    otherResourceMapper = (ResourceOtherMapper) getApplicationContext().getBean(ResourceOtherMapper.class);
                }
                if (europeResourceMapper == null) {
                    europeResourceMapper = (ResourceEuropeMapper) getApplicationContext().getBean(ResourceEuropeMapper.class);
                }


                Html html = new Html(s);
                String url = html.xpath("*/a[@class='video-pic loading']/@data-original").toString();
                String title = html.xpath("*/a[@class='video-pic loading']/@title").toString();
                System.out.println(url);
                System.out.println(title);
                if (bigTitle != null && minTitle != null) {
                    Example example2 = new Example(ResourceMovieTitle.class);
                    example2.createCriteria().andEqualTo("title", title);
                    int count = resourceMovieTitleMapper.selectCountByExample(example2);
                    if (count <= 0) {
                        ResourceMovieTitle resourceMovieTitle = new ResourceMovieTitle();
                        resourceMovieTitle.setTitle(title);
                        resourceMovieTitle.setTitleUrl(url);
                        resourceMovieTitle.setIndexColumn(bigTitle);
                        resourceMovieTitle.setType(minTitle);
                        resourceMovieTitleMapper.insert(resourceMovieTitle);
                        System.out.println("====新增成功====");
                    } else {
                        System.out.println("====新增重复====");
                    }

                    //************************************************更新电影表********************************
                    //更新电影表
                    switch(bigTitle) {
                        case MovieResourceType.ASIANHM:
                            Example example = new Example(ResourceAsianhm.class);
                            example.createCriteria().andLike("name",title);
                            List<ResourceAsianhm> AsianhmResourceList = asianhmResourceMapper.selectByExample(example);
                            if (!AsianhmResourceList.isEmpty()) {
                                ResourceAsianhm asianhmResource = AsianhmResourceList.get(0);
                                asianhmResource.setTitleUrl(url);
                                asianhmResourceMapper.updateByPrimaryKey(asianhmResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        case MovieResourceType.ASIANWM:
                            Example example8 = new Example(ResourceAsianwm.class);
                            example8.createCriteria().andLike("name",title);
                            List<ResourceAsianwm> AsianwmResourceList = asianwmResourceMapper.selectByExample(example8);
                            if (!AsianwmResourceList.isEmpty()) {
                                ResourceAsianwm asianwmResource = AsianwmResourceList.get(0);
                                asianwmResource.setTitleUrl(url);
                                asianwmResourceMapper.updateByPrimaryKey(asianwmResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        case MovieResourceType.CARTOON:
                            Example example3 = new Example(ResourceCartoon.class);
                            example3.createCriteria().andLike("name",title);
                            List<ResourceCartoon> CartoonResourceList = cartoonResourceMapper.selectByExample(example3);
                            if (!CartoonResourceList.isEmpty()) {
                                ResourceCartoon cartoonResource = CartoonResourceList.get(0);
                                cartoonResource.setTitleUrl(url);
                                cartoonResourceMapper.updateByPrimaryKey(cartoonResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        case MovieResourceType.DOMESTIC:
                            Example example4 = new Example(ResourceDomestic.class);
                            example4.createCriteria().andLike("name",title);
                            List<ResourceDomestic> DomesticResourceList = domesticResourceMapper.selectByExample(example4);
                            if (!DomesticResourceList.isEmpty()) {
                                ResourceDomestic domesticResource = DomesticResourceList.get(0);
                                domesticResource.setTitleUrl(url);
                                domesticResourceMapper.updateByPrimaryKey(domesticResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        case MovieResourceType.EUROPE:
                            Example example5 = new Example(ResourceEurope.class);
                            example5.createCriteria().andLike("name",title);
                            List<ResourceEurope> EuropeResourceList = europeResourceMapper.selectByExample(example5);
                            if (!EuropeResourceList.isEmpty()) {
                                ResourceEurope europeResource = EuropeResourceList.get(0);
                                europeResource.setTitleUrl(url);
                                europeResourceMapper.updateByPrimaryKey(europeResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        case MovieResourceType.STAR:
                            Example example6 = new Example(ResourceStar.class);
                            example6.createCriteria().andLike("name",title);
                            List<ResourceStar> StarResourceList = starResourceMapper.selectByExample(example6);
                            if (!StarResourceList.isEmpty()) {
                                ResourceStar starResource = StarResourceList.get(0);
                                starResource.setTitleUrl(url);
                                starResourceMapper.updateByPrimaryKey(starResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        default:
                            Example example7 = new Example(ResourceOther.class);
                            example7.createCriteria().andLike("name",title);
                            List<ResourceOther> OtherResourceList = otherResourceMapper.selectByExample(example7);
                            if (!OtherResourceList.isEmpty()) {
                                ResourceOther otherResource = OtherResourceList.get(0);
                                otherResource.setTitleUrl(url);
                                otherResourceMapper.updateByPrimaryKey(otherResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                    }
                }
            }
        }
        //************************************************封面处理********************************

        //子目录/更多
        List<String> urlList3 = page.getHtml().xpath("*//a[@class='text-muted']/@href").all();
        page.putField("urlList3", urlList3);

        //内容
        List<String> urlList2 =   page.getHtml().xpath("*//a[@class='video-pic loading']/@href").all();
        page.putField("urlList2",urlList2);

        //下一页
        List<String> urlList = page.getHtml().xpath("*/li/a[@class='next pagegbk']/@href").all();
        page.putField("urlList", urlList);

        System.out.println("url-------------->" + urlList.size() + urlList3.size());
        page.addTargetRequests(urlList);
        page.addTargetRequests(urlList2);
        page.addTargetRequests(urlList3);
    }

    public static String subString(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return "not";
        }
        if (strEndIndex < 0) {
            return "not";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }

    public void begin() {
        //设置代理
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("324.424.32.24", 4242)
                , new Proxy("24.102.234.102", 4256)
                , new Proxy("103.103.243.103", 1246)
                , new Proxy("104.342.332.104", 8643)
                , new Proxy("344.105.243.102", 7568)
                , new Proxy("244.102.42.102", 3567)));

        Spider.create(new ResourceMovieTitleServiceImpl())
                //去重
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.886pi.com/html/2/")
                .addUrl("https://www.552en.com/html/1/")
                .addUrl("https://www.552en.com/html/8/")
                .addUrl("https://www.552en.com/html/5/")
                .addUrl("https://www.552en.com/html/3/")
                .addUrl("https://www.552en.com/html/4/")
                .addUrl("https://www.552en.com/html/3/")
                .addUrl("https://www.552en.com/html/3/")
                .addUrl("https://www.552en.com/html/3/")
                .addUrl("https://www.552en.com")
                //开启5个线程抓取
                .thread(8)
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
