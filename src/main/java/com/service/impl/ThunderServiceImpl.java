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
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
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
            if (resourceThunderMapper == null) {
                resourceThunderMapper = (ResourceThunderMapper) getApplicationContext().getBean(ResourceThunderMapper.class);
            }
            //保存zz
            try {
                String thunderUrl =   page.getHtml().xpath("*//a[@class='btn btn-sm btn-primary']/@href").toString();
                page.putField("thunderUrl============>>>",thunderUrl);

                if (thunderUrl != null){
                    if (resourceThunderNotMapper == null) {
                        resourceThunderNotMapper = (ResourceThunderNotMapper) getApplicationContext().getBean(ResourceThunderNotMapper.class);
                    }
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
                }else{
                    if (resourceThunderNotMapper == null) {
                        resourceThunderNotMapper = (ResourceThunderNotMapper) getApplicationContext().getBean(ResourceThunderNotMapper.class);
                    }
                    ResourceThunderNot not = new ResourceThunderNot();
                    not.setIsDown("0");
                    not.setThunder(page.getUrl().toString());
                    resourceThunderNotMapper.insertSelective(not);
                    page.setSkip(true);
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
                new Proxy("324.424.32.24", 4242)));

        Spider.create(new ThunderServiceImpl()).thread(5)
                .setDownloader(new SeleniumDownloader("F:/phantomjs/phantomjs-2.1.1-windows/bin/phantomjs.exe"))
                .addUrl("https://www.886pi.com/html/2/")
                .addUrl("https://www.552en.com/html/1/")
                .addUrl("https://www.552en.com/html/8/")
                /*.addUrl("https://www.552en.com/html/5/")
                .addUrl("https://www.552en.com/html/3/")
                .addUrl("https://www.552en.com/html/4/")
                .addUrl("https://www.552en.com/html/3/")
                .addUrl("https://www.552en.com/html/3/")
                .addUrl("https://www.552en.com/html/3/")*/
                .runAsync();
    }

    public ApplicationContext getApplicationContext() {
        return SpringContextUtils.getApplicationContext();
    }

    @Override
    public Site getSite() {
        /*if (null == site) {
            site = Site.me().setDomain("886pi.com").setSleepTime(0);
        }*/
        return site;
    }


}
