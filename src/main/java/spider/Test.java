package spider;

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

public class Test implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<String> list =   page.getHtml().xpath("*/html/html()").all();
        page.putField("html",list);

        List<String> urlList =   page.getHtml().xpath("*//a[@class='video-pic swiper-lazy']/@href").all();
        page.putField("urlList",urlList);
        for (String s : urlList){
            String url = "https://www.886er.com"+s;
            System.getProperties().setProperty("webdriver.chrome.driver", "G:/chromedriver_win32/chromedriver.exe");
            WebDriver webDriver = new ChromeDriver();
            webDriver.get(url);
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            System.out.println(webElement.getAttribute("outerHTML"));
            String htm = webElement.getAttribute("outerHTML");
            webDriver.close();
           String thunderUrl = subString(htm,"thunder://","\"> &nbsp; <span id=\"[down:num]");

            System.out.println("-----------==============>"+"thunder://"+thunderUrl);

        }

        /*List<String> gifList =   page.getHtml().xpath("/div[@class='box-video-text-list']/text()").all();
        page.putField("gifList",gifList);*/

/*       List<String> list =   page.getHtml().xpath("/head/html()").all();
        page.putField("head",list);

        List<String> bodyList =   page.getHtml().xpath("/body/html()").all();
        page.putField("body",bodyList);*/

        /*String columnList =   page.getHtml().xpath("/li[@class='active']/a/text()").toString();
        page.putField("column",columnList);

        page.putField("columnFull",page.getHtml().xpath("/div[@class='play_nav hidden-xs']/a/text()").all());

       String titleList =   page.getHtml().xpath("/div[@class='player_title']/h1/html()").toString();
        page.putField("titleList",titleList);*/


        /*if (imgList.isEmpty()) {
            System.out.println("s==============跳过");
            page.setSkip(true);
        }else {
                System.out.println("s=============="+imgList);
        }*/


        // 部分三：从页面发现后续的url地址来抓取
       /* List<String> list = page.getHtml().xpath("/a/@href").all();
        System.out.println("url-------------->"+list.size());
        page.addTargetRequests(page.getHtml().xpath("/a/@href").all());*/
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

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //设置代理
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("324.424.32.24",8888)
                ,new Proxy("24.102.234.102",8888)
                ,new Proxy("103.103.243.103",8888)
                ,new Proxy("104.342.332.104",8888)
                ,new Proxy("344.105.243.102",8888)
                ,new Proxy("244.102.42.102",8888)));

        Spider.create(new Test())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.886er.com/vod/17/11675-1.html")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

    }
}
