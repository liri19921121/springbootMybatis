package spider;

import spider.phantomjsUtil.SeleniumDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * 把webmagicSelenium打成jar包加进来，安装phomjs
 */
public class TestWebmagicSelenium implements PageProcessor {
    private Site site;

    @Override
    public void process(Page page) {
        System.out.println("----------");
        List<String> list =   page.getHtml().xpath("*/html/html()").all();
       /* page.putField("html",list);*/

        String href =   page.getHtml().xpath("*//a[@class='btn btn-sm btn-primary']/@href").toString();
        page.putField("href===",href);

    }

    @Override
    public Site getSite() {
        if (null == site) {
            site = Site.me().setDomain("huaban.com").setSleepTime(0);
        }
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TestWebmagicSelenium()).thread(5)
                .setDownloader(new SeleniumDownloader("F:/phantomjs/phantomjs-2.1.1-windows/bin/phantomjs.exe"))
                .addUrl("https://www.552en.com/vod/27/11365-1.html")
                .runAsync();
    }
}
