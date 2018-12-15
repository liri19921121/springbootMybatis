package spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class Test3 implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来

        List<String> pageList =   page.getHtml().xpath("*//a[@class='video-pic loading']/@href").all();
        List<String> nextPagegbkList =   page.getHtml().xpath("*//a[@class='next pagegbk']/@href").all();

        if (pageList == null) {
            System.out.println("s==============跳过");
            page.setSkip(true);
        }else {
                System.out.println("s=============="+pageList);
                //写入本地
        }

        // 部分三：从页面发现后续的url地址来抓取
        //下一页地址
       /* List<String> afterUrlList =   page.getHtml().xpath("/div[@class='details-page clearfix']/ul[@class='clearfix']/li[@class='col-md-6']/a/@href").all();
        page.putField("afterUrl",afterUrlList.get(0));
        List<String> list = new ArrayList<>();
        list.add(afterUrlList.get(0));
        page.addTargetRequests(list);*/
    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new Test3())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.886pi.com/html/news/69/")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
