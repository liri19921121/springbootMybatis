package spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class TextTest2 implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(5).setSleepTime(100);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        //封面
        List<String> urlList2 =   page.getHtml().xpath("*/li[@class='col-md-2 col-sm-3 col-xs-4 ']/a[@class='video-pic loading']").all();
        page.putField("urlList2",urlList2);
        for (String s : urlList2){
            Html html = new Html(s);
            String url = html.xpath("*/a[@class='video-pic loading']/@data-original").toString();
            String title = html.xpath("*/a[@class='video-pic loading']/@title").toString();
            System.out.println(url);
            System.out.println(title);
        }

        //大栏目
        String bigTitle =   page.getHtml().xpath("*//li[@class='active']/a[@target='_blank']/html()").toString();
        page.putField("bigTitle",bigTitle);

        String minTitle =   page.getHtml().xpath("*//a[@data='order-addtime']/html()").toString();
        page.putField("minTitle",minTitle);


    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new TextTest2())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.552za.com/html/61/")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
