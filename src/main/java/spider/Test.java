package spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class Test implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<String> list =   page.getHtml().xpath("*/html/html()").all();
        page.putField("html",list);

        List<String> imgList =   page.getHtml().xpath("*/body/li[@class='swiper-slide']/text()").all();
        page.putField("imgList",imgList);

        List<String> gifList =   page.getHtml().xpath("*/div[@class='box-video-text-list']/text()").all();
        page.putField("gifList",gifList);

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


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new Test())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.886er.com/vod/17/11675-1.html")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
