package com.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class GithubRepoPageProcessor implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        List<String> list =  page.getHtml().xpath("*//div[@class='description']/p[@class='line-clamp']/text()").all();
        page.putField("selectContent", list);
        if (page.getResultItems().get("selectContent") == null) {
            //skip this page
            System.out.println("跳过=================================================================");
            page.setSkip(true);
        }else {
            int i = 0;
            for (String s:list){
                i++;
                System.out.println("第"+i+"条:"+s);
            }
        }


        // 部分三：从页面发现后续的url地址来抓取
        List<String> urlList = page.getHtml().xpath("*//div[@class='content']/a[@class='header']/@href").all();
        System.out.println("url-------------->"+urlList.size());
        page.addTargetRequests(page.getHtml().xpath("*//div[@class='content']/a[@class='header']/@href").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://my.oschina.net/flashsword")
                /*.addPipeline(new JsonFilePipeline("G:\\webmagic\\"))*/
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

    }
}
