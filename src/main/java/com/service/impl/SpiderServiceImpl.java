package com.service.impl;

import com.common.BaseService.SpringContextUtils;
import com.pojo.MovieResources;
import com.service.MovieResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

@Service
public class SpiderServiceImpl  implements PageProcessor  {

    @Autowired
    private MovieResourcesService movieResourcesService;

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {

        List<String> scriptList =    page.getHtml().xpath("*/script").all();

        if (scriptList.isEmpty()) {
            page.setSkip(true);
        }else {
            for (String s:scriptList){
                String downurls = subString(s,"var downurls = \"","#");
                if (!downurls.equals("not")){

                    System.out.println("====================================0"+downurls);
                    System.out.println("====================================1"+downurls.substring(0,downurls.indexOf("https")));
                    System.out.println("====================================2"+downurls.substring(downurls.indexOf("https"),downurls.length()));

                    String url = downurls.substring(downurls.indexOf("https"),downurls.length());
                    String name = downurls.substring(0,downurls.indexOf("https"));
                    if(movieResourcesService == null){
                        movieResourcesService = (MovieResourcesService)  getApplicationContext().getBean(MovieResourcesServiceImpl.class);
                    }
                    movieResourcesService.insert(name,url);

                }
            }
        }


        // 部分三：从页面发现后续的url地址来抓取
        List<String> list = page.getHtml().xpath("*/a/@href").all();
        System.out.println("url-------------->"+list.size());
        page.addTargetRequests(page.getHtml().xpath("*/a/@href").all());
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


    public void begin(){
        Spider.create(new SpiderServiceImpl())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.885nu.com/vod/29/11689-1.html")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }

    public ApplicationContext getApplicationContext(){
        return  SpringContextUtils.getApplicationContext();
    }

    @Override
    public Site getSite() {
        return site;
    }

}
