package com.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.common.BaseService.SpringContextUtils;
import com.service.MovieResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
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
public class SpiderServiceImpl implements PageProcessor {

    @Autowired
    private MovieResourcesService movieResourcesService;

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {

        List<String> scriptList = page.getHtml().xpath("*/script").all();

        String column = page.getHtml().xpath("*/li[@class='active']/a/text()").toString();
        page.putField("column", column);

        List<String> fullList = page.getHtml().xpath("*/div[@class='play_nav hidden-xs']/a/text()").all();

        String fullCcolumn = fullList.toString();
        page.putField("fullCcolumn", fullCcolumn);

        String type = "";
        if (!fullList.isEmpty()) {
            type = fullList.get(fullList.size() - 1);
        }

        String name = page.getHtml().xpath("*/div[@class='player_title']/h1/html()").toString();
        page.putField("titleList", name);

        if (StringUtils.isEmpty(name)) {
            page.setSkip(true);
        } else {
            for (String s : scriptList) {
                String downurls = subString(s, "var downurls = \"", "#");
                if (!downurls.equals("not")) {

                    String url = downurls.substring(downurls.indexOf("https"), downurls.length());
                    if (movieResourcesService == null) {
                        movieResourcesService = (MovieResourcesService) getApplicationContext().getBean(MovieResourcesServiceImpl.class);
                    }
                    movieResourcesService.insert(name, url, column, fullCcolumn, type);
                }
            }
        }

        // 部分三：从页面发现后续的url地址来抓取
       /* List<String> list = page.getHtml().xpath("/a/@href").all();*/

        //首页目录
       /* List<String> urlList4 =   page.getHtml().xpath("//a[@target='_blank']/@href").all();
        page.putField("urlList4",urlList4);*/

        //子目录/更多
        List<String> urlList3 =   page.getHtml().xpath("*//a[@class='text-muted']/@href").all();
        page.putField("urlList3",urlList3);

        //内容
        List<String> urlList2 =   page.getHtml().xpath("*//a[@class='video-pic loading']/@href").all();
        page.putField("urlList2",urlList2);

        //下一页
        List<String> urlList =   page.getHtml().xpath("*/li/a[@class='next pagegbk']/@href").all();
        page.putField("urlList",urlList);

        System.out.println("url-------------->" + urlList.size() + urlList2.size()+ urlList3.size());
        page.addTargetRequests(urlList);
        page.addTargetRequests(urlList2);
        page.addTargetRequests(urlList3);
        /*page.addTargetRequests(urlList4);*/
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
                new Proxy("324.424.32.24",4242)
                ,new Proxy("24.102.234.102",4256)
                ,new Proxy("103.103.243.103",1246)
                ,new Proxy("104.342.332.104",8643)
                ,new Proxy("344.105.243.102",7568)
                ,new Proxy("244.102.42.102",3567)));

        Spider.create(new SpiderServiceImpl())
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
