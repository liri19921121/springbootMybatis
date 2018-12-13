package spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class Test implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
       List<String> list =   page.getHtml().xpath("*/head/html()").all();
        page.putField("head",list);

        List<String> bodyList =   page.getHtml().xpath("*/body/html()").all();
        page.putField("body",bodyList);

        String columnList =   page.getHtml().xpath("*/li[@class='active']/a/text()").toString();
        page.putField("columnList",columnList);

        page.putField("columnList2",page.getHtml().xpath("*/div[@class='play_nav hidden-xs']/a/text()").all());

       String titleList =   page.getHtml().xpath("*/div[@class='player_title']/h1/html()").toString();
        page.putField("titleList",titleList);


        if (titleList.isEmpty()) {
            System.out.println("s==============跳过");
            page.setSkip(true);
        }else {
                System.out.println("s=============="+titleList);
        }


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
