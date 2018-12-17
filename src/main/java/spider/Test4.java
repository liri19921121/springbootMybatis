package spider;

import com.common.utils.SpiderUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.List;

public class Test4 implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<String> htm =   page.getHtml().xpath("*/html/html()").all();
        page.putField("html==========",htm);

        //标题
        /*String title =   page.getHtml().xpath("//div[@class='box cat_pos clearfix']/img/@src").toString();
        page.putField("html",list);*/

        //图片
        List<String> list =   page.getHtml().xpath("*//div[@class='content']/img/@src").all();
        page.putField("html",list);

        //下一页
        List<String> next =   page.getHtml().xpath("*//span[@class='next']/p/a/@href").all();
        page.putField("next",next);
        //面
        List<String> url =   page.getHtml().xpath("*//div[@class='box list channel']//a/@href").all();
        page.putField("url",url);
        //下一页
        List<String> nextPage =   page.getHtml().xpath("*//li[@class='page-next']/a/@href").all();
        page.putField("nextPage",nextPage);
        page.addTargetRequests(url);
        page.addTargetRequests(nextPage);
        page.addTargetRequests(next);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        //设置代理
        Spider.create(new Test4())
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://aqhfx.com/article/21.html")
                .addUrl("http://aqhfx.com/article/22.html")
                .addUrl("http://aqhfx.com/article/23.html")
                .addUrl("http://aqhfx.com/article/24.html")
                .addUrl("http://aqhfx.com/article/25.html")
                .addUrl("http://aqhfx.com/article/26.html")
                .addUrl("http://aqhfx.com/article/27.html")
                .addUrl("http://aqhfx.com/article/28.html")

                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

    }
}
