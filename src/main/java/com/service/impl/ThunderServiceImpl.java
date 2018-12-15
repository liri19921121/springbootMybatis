package com.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.common.BaseService.SpringContextUtils;
import com.service.MovieResourcesService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import spider.Test2;
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
public class ThunderServiceImpl implements PageProcessor {

    @Autowired
    private MovieResourcesService movieResourcesService;

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {

        List<String> list =   page.getHtml().xpath("*/html/html()").all();
        page.putField("html",list);

        List<String> urlList =   page.getHtml().xpath("*//a[@class='video-pic swiper-lazy']/@href").all();
        page.putField("urlList",urlList);
        for (String s : urlList){
            String url = "https://www.886er.com"+s;
            System.getProperties().setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
            WebDriver webDriver = new ChromeDriver();
            webDriver.get(url);
            WebElement webElement = webDriver.findElement(By.xpath("/html"));

            Html html = new Html( webElement.getAttribute("outerHTML"));

            String column = html.xpath("*/li[@class='active']/a/text()").toString();
            List<String> fullList = html.xpath("*/div[@class='play_nav hidden-xs']/a/text()").all();
            String type = "";
            if (!fullList.isEmpty()) {
                type = fullList.get(fullList.size() - 1);
            }
            String name = page.getHtml().xpath("*/div[@class='player_title']/h1/html()").toString();

            System.out.println("column==============="+column);
            System.out.println("fullList==============="+fullList.toString());
            System.out.println("type==============="+type);
            System.out.println("name==============="+name);

            String htm = webElement.getAttribute("outerHTML")
                    .replaceAll(" ","").replaceAll("\\s*", "")
                    .replaceAll(" +","").replace("text\"value=\"thunder://","18682012295").replace("=\"></td><tdalign=\"right\"><aclass=\"btnbtn-smbtn-primary\"id=\"","13145810058");
            System.out.println("页面开始----------------------------------------------------------------------------->"
                    +htm+"<-----------------------------------------------页面结束");

            webDriver.close();
            String thunderUrl = subString(htm,"18682012295","13145810058");

            System.out.println("-----------==============>"+"thunder://"+thunderUrl);

        }
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
                new Proxy("244.343.322.244",5332)
                ,new Proxy("4245.432.421.523",3242)
        ));

        Spider.create(new ThunderServiceImpl())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.886er.com/vod/17/11675-1.html")
                //开启5个线程抓取
                /*.thread(1)*/
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
