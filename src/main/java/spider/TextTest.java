package spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class TextTest implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        //大栏目
        List<String> urlList = page.getHtml().xpath("*/div[@class='more pull-right']/a[@class='text-muted']/@href").all();
        page.putField("urlList", urlList);

        //页面连接
        List<String> yemianList = page.getHtml().xpath("*/div[@class='layout-box clearfix']//a/@href").all();
        page.putField("yemianList", yemianList);

        //下一页
        String nextUrl = page.getHtml().xpath("*/a[@class='next pagegbk']/@href").toString();
        page.putField("nextUrl", nextUrl);


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

    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new TextTest())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.552en.com/html/news/46/")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
