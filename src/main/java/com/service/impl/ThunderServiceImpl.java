package com.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.common.BaseService.SpringContextUtils;
import com.common.utils.SpiderUtils;
import com.mapper.ResourceThunderMapper;
import com.mapper.ResourceThunderNotMapper;
import com.pojo.AsianhmResource;
import com.pojo.ResourceThunder;
import com.pojo.ResourceThunderNot;
import com.service.MovieResourcesService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import spider.Test2;
import tk.mybatis.mapper.entity.Example;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

@Service
public class ThunderServiceImpl implements PageProcessor {

    @Autowired
    private ResourceThunderMapper resourceThunderMapper;

    @Autowired
    private ResourceThunderNotMapper resourceThunderNotMapper;


    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

        List<String> scriptList = page.getHtml().xpath("*/script").all();

        String column = page.getHtml().xpath("*/li[@class='active']/a/text()").toString();

        List<String> fullList = page.getHtml().xpath("*/div[@class='play_nav hidden-xs']/a/text()").all();
        String fullCcolumn = fullList.toString();
        String type = "";
        if (!fullList.isEmpty()) {
            type = fullList.get(fullList.size() - 1);
        }

        String name = page.getHtml().xpath("*/div[@class='player_title']/h1/html()").toString();

        if (StringUtils.isEmpty(name)) {
            page.setSkip(true);
        } else {
            for (String s : scriptList) {
                String downurls = SpiderUtils.subString(s, "var downurls = \"", "#");
                if (!downurls.equals("not")) {
                    if (resourceThunderMapper == null) {
                        resourceThunderMapper = (ResourceThunderMapper) getApplicationContext().getBean(ResourceThunderMapper.class);
                    }
                    //保存zz
                    try {
                        WebDriver driver = SpiderUtils.getPhantomJSDriver();
                        System.out.println("爬取页面：=======" + page.getUrl().toString());
                        driver.get(page.getUrl().toString());

                        WebElement webElement = driver.findElement(By.xpath("/html"));
                        String thm = webElement.getAttribute("outerHTML");
                        System.out.println("outerHTML=======" + thm);
                        driver.quit();

                        String htm = thm
                                .replaceAll(" ", "").replaceAll("\\s*", "")
                                .replaceAll(" +", "").replace("text\"value=\"thunder://", "18682012295").replace("=\"></td><tdalign=\"right\"><aclass=\"btnbtn-smbtn-primary\"id=\"", "13145810058");

                        String thunderUrl = "thunder://" + SpiderUtils.subString(htm, "18682012295", "13145810058");

                        System.out.println("爬取到-----------==============>" + thunderUrl);

                        if (resourceThunderNotMapper == null) {
                            resourceThunderNotMapper = (ResourceThunderNotMapper) getApplicationContext().getBean(ResourceThunderNotMapper.class);
                        }

                        if (thunderUrl.equals("thunder://not")) {
                            ResourceThunderNot not = new ResourceThunderNot();
                            not.setIsDown("0");
                            not.setThunder(page.getUrl().toString());
                            resourceThunderNotMapper.insertSelective(not);
                            page.setSkip(true);
                        } else {
                            //判重
                            Example example = new Example(ResourceThunder.class);
                            example.createCriteria().andEqualTo("thunder", thunderUrl);
                            int count = resourceThunderMapper.selectCountByExample(example);
                            if (count <= 0) {
                                ResourceThunder resourceThunder = new ResourceThunder();
                                resourceThunder.setIndexColumn(column);
                                resourceThunder.setThunder(thunderUrl);
                                resourceThunder.setTitle(name);
                                resourceThunder.setType(type);
                                resourceThunderMapper.insertSelective(resourceThunder);
                                System.out.println("新增成功");
                            } else {
                                System.out.println("新增重复");
                            }
                        }
                    } catch (Exception e) {
                        //当前页面为
                        //保存到爬取失败列表
                        if (resourceThunderNotMapper == null) {
                            resourceThunderNotMapper = (ResourceThunderNotMapper) getApplicationContext().getBean(ResourceThunderNotMapper.class);
                        }
                        ResourceThunderNot not = new ResourceThunderNot();
                        not.setIsDown("1");
                        not.setThunder(page.getUrl().toString());
                        resourceThunderNotMapper.insertSelective(not);
                        page.setSkip(true);
                    }


                }
            }
        }
        //子目录/更多
        List<String> urlList3 = page.getHtml().xpath("*//a[@class='text-muted']/@href").all();
        page.putField("urlList3", urlList3);

        //内容
        List<String> urlList2 = page.getHtml().xpath("*//a[@class='video-pic loading']/@href").all();
        page.putField("urlList2", urlList2);

        //下一页
        List<String> urlList = page.getHtml().xpath("*/li/a[@class='next pagegbk']/@href").all();
        page.putField("urlList", urlList);

        System.out.println("url-------------->" + urlList.size() + urlList2.size() + urlList3.size());
        page.addTargetRequests(urlList);
        page.addTargetRequests(urlList2);
        page.addTargetRequests(urlList3);
    }

    public void begin() {
        //设置代理
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("324.424.32.24", 4242)
                , new Proxy("24.102.234.102", 4256)
                , new Proxy("103.103.243.103", 1246)
                , new Proxy("104.342.332.104", 8643)
                , new Proxy("344.105.243.102", 7568)
                , new Proxy("244.102.42.102", 3567)));

        Spider.create(new ThunderServiceImpl())
                //去重
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://www.886pi.com/html/2/")
                .addUrl("https://www.552en.com/html/1/")
                .addUrl("https://www.552en.com/html/8/")
                .addUrl("https://www.552en.com/html/5/")
                .addUrl("https://www.552en.com/html/3/")
                .addUrl("https://www.552en.com/html/4/")
                .addUrl("https://www.552en.com/html/3/")
                .addUrl("https://www.552en.com/html/3/")
                .addUrl("https://www.552en.com/html/3/")
                //开启5个线程抓取
                .thread(8)
                //启动爬虫
                .run();
    }

    public ApplicationContext getApplicationContext() {
        return SpringContextUtils.getApplicationContext();
    }

    @Override
    public Site getSite() {
        return site;
    }


}
