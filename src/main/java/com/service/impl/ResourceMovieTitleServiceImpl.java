package com.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.common.BaseService.SpringContextUtils;
import com.common.constant.MovieResourceType;
import com.mapper.*;
import com.pojo.*;
import com.service.MovieResourcesService;
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
    private AsianhmResourceMapper asianhmResourceMapper;

    @Autowired
    private AsianwmResourceMapper asianwmResourceMapper;

    @Autowired
    private CartoonResourceMapper cartoonResourceMapper;

    @Autowired
    private DomesticResourceMapper domesticResourceMapper;

    @Autowired
    private StarResourceMapper starResourceMapper;

    @Autowired
    private OtherResourceMapper otherResourceMapper;

    @Autowired
    private EuropeResourceMapper europeResourceMapper;

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
                    asianhmResourceMapper = (AsianhmResourceMapper) getApplicationContext().getBean(AsianhmResourceMapper.class);
                }
                if (asianwmResourceMapper == null) {
                    asianwmResourceMapper = (AsianwmResourceMapper) getApplicationContext().getBean(AsianwmResourceMapper.class);
                }
                if (cartoonResourceMapper == null) {
                    cartoonResourceMapper = (CartoonResourceMapper) getApplicationContext().getBean(CartoonResourceMapper.class);
                }
                if (domesticResourceMapper == null) {
                    domesticResourceMapper = (DomesticResourceMapper) getApplicationContext().getBean(DomesticResourceMapper.class);
                }
                if (starResourceMapper == null) {
                    starResourceMapper = (StarResourceMapper) getApplicationContext().getBean(StarResourceMapper.class);
                }
                if (otherResourceMapper == null) {
                    otherResourceMapper = (OtherResourceMapper) getApplicationContext().getBean(OtherResourceMapper.class);
                }
                if (europeResourceMapper == null) {
                    europeResourceMapper = (EuropeResourceMapper) getApplicationContext().getBean(EuropeResourceMapper.class);
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
                            Example example = new Example(AsianhmResource.class);
                            example.createCriteria().andLike("name",title);
                            List<AsianhmResource> AsianhmResourceList = asianhmResourceMapper.selectByExample(example);
                            if (!AsianhmResourceList.isEmpty()) {
                                AsianhmResource asianhmResource = AsianhmResourceList.get(0);
                                asianhmResource.setTitleUrl(url);
                                asianhmResourceMapper.updateByPrimaryKey(asianhmResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        case MovieResourceType.ASIANWM:
                            Example example8 = new Example(AsianwmResource.class);
                            example8.createCriteria().andLike("name",title);
                            List<AsianwmResource> AsianwmResourceList = asianwmResourceMapper.selectByExample(example8);
                            if (!AsianwmResourceList.isEmpty()) {
                                AsianwmResource asianwmResource = AsianwmResourceList.get(0);
                                asianwmResource.setTitleUrl(url);
                                asianwmResourceMapper.updateByPrimaryKey(asianwmResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        case MovieResourceType.CARTOON:
                            Example example3 = new Example(CartoonResource.class);
                            example3.createCriteria().andLike("name",title);
                            List<CartoonResource> CartoonResourceList = cartoonResourceMapper.selectByExample(example3);
                            if (!CartoonResourceList.isEmpty()) {
                                CartoonResource cartoonResource = CartoonResourceList.get(0);
                                cartoonResource.setTitleUrl(url);
                                cartoonResourceMapper.updateByPrimaryKey(cartoonResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        case MovieResourceType.DOMESTIC:
                            Example example4 = new Example(DomesticResource.class);
                            example4.createCriteria().andLike("name",title);
                            List<DomesticResource> DomesticResourceList = domesticResourceMapper.selectByExample(example4);
                            if (!DomesticResourceList.isEmpty()) {
                                DomesticResource domesticResource = DomesticResourceList.get(0);
                                domesticResource.setTitleUrl(url);
                                domesticResourceMapper.updateByPrimaryKey(domesticResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        case MovieResourceType.EUROPE:
                            Example example5 = new Example(EuropeResource.class);
                            example5.createCriteria().andLike("name",title);
                            List<EuropeResource> EuropeResourceList = europeResourceMapper.selectByExample(example5);
                            if (!EuropeResourceList.isEmpty()) {
                                EuropeResource europeResource = EuropeResourceList.get(0);
                                europeResource.setTitleUrl(url);
                                europeResourceMapper.updateByPrimaryKey(europeResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        case MovieResourceType.STAR:
                            Example example6 = new Example(StarResource.class);
                            example6.createCriteria().andLike("name",title);
                            List<StarResource> StarResourceList = starResourceMapper.selectByExample(example6);
                            if (!StarResourceList.isEmpty()) {
                                StarResource starResource = StarResourceList.get(0);
                                starResource.setTitleUrl(url);
                                starResourceMapper.updateByPrimaryKey(starResource);
                                System.out.println("====添加成功====");
                            } else {
                                System.out.println("====添加失败====");
                            }
                            break;
                        default:
                            Example example7 = new Example(OtherResource.class);
                            example7.createCriteria().andLike("name",title);
                            List<OtherResource> OtherResourceList = otherResourceMapper.selectByExample(example7);
                            if (!OtherResourceList.isEmpty()) {
                                OtherResource otherResource = OtherResourceList.get(0);
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

        //下一页
        List<String> urlList = page.getHtml().xpath("*/li/a[@class='next pagegbk']/@href").all();
        page.putField("urlList", urlList);

        System.out.println("url-------------->" + urlList.size() + urlList3.size());
        page.addTargetRequests(urlList);
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
