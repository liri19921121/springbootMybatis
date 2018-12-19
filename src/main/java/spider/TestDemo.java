package spider;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.List;

public class TestDemo implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        String list =   page.getHtml().xpath("*/html/html()").toString();
        page.putField("html",list);

        List<String> links = page.getHtml().links().regex("http://my\\.oschina\\.net/flashsword/blog/\\d+").all();
        page.addTargetRequests(links);
        page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());
        page.putField("content", page.getHtml().$("div.content").toString());
        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());

        if (links.isEmpty()) {
            System.out.println("s==============跳过");
            page.setSkip(true);
        }else {
                System.out.println("s=============="+links.toString());
        }


        //dosomething，保存到数据库什么的


        // 部分三：从页面发现后续的url地址来抓取
        List<String> pageList = page.getHtml().xpath("/a/@href").all();
        page.addTargetRequests(pageList);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //设置代理
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("244.343.322.244",5332)
                ,new Proxy("4245.432.421.523",3242)
        ));

        Spider.create(new Test2())
                //从"https://www.csdn.net/"开始抓
                .addUrl("http://my.oschina.net/flashsword/blog")
                //配置代理
               /* .setDownloader(httpClientDownloader)*/
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

    }
}
