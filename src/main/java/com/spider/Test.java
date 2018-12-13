package com.spider;

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
       /* List<String> list =   page.getHtml().xpath("/head/html()").all();
        page.putField("head",list);

        List<String> bodyList =   page.getHtml().xpath("/body/html()").all();
        page.putField("body",bodyList);*/

        List<String> scriptList =    page.getHtml().xpath("*/script").all();
        page.putField("scriptList",scriptList);

        if (page.getResultItems().get("scriptList") == null) {
            page.setSkip(true);
        }else {
            for (String s:scriptList){
                String downurls = subString(s,"var downurls =",";");
                String httpurl = subString(s,"var httpurl =",";");
                if (!downurls.equals("not")){
                    System.out.println("===================================="+downurls);
                }
                /*if (!httpurl.equals("not")){
                    System.out.println("===================================="+httpurl);
                }*/

            }
        }


        // 部分三：从页面发现后续的url地址来抓取
       /* List<String> list = page.getHtml().xpath("/html/body//a/@href").all();
        System.out.println("url-------------->"+list.size());
        page.addTargetRequests(page.getHtml().xpath("/html/body//a/@href").all());*/
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

        Spider.create(new Test())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.885nu.com/vod/29/11689-1.html")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
