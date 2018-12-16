package com.controller;

import com.pojo.ResourceMovieTitle;
import com.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/spider"})
public class Spider {

    @Autowired
    private SpiderServiceImpl spiderServiceImpl;

    @Autowired
    private ImgServiceImpl imgServiceImpl;

    @Autowired
    private ThunderServiceImpl thunderService;

    @Autowired
    private TextServiceImpl textServiceImpl;

    @Autowired
    private ResourceMovieTitleServiceImpl resourceMovieTitleServiceImpl;

    @PostMapping(value = {"/moiveBegin"})
    public void moiveBegin(HttpServletRequest request) throws Exception{
        spiderServiceImpl.begin();
    }

    @PostMapping(value = {"/imgBegin"})
    public void imgBegin(HttpServletRequest request) throws Exception{
        imgServiceImpl.begin();
    }

    @PostMapping(value = {"/thunderBegin"})
    public void thunderBegin(HttpServletRequest request) throws Exception{
        thunderService.begin();
    }

    @PostMapping(value = {"/textBegin"})
    public void textBegin(HttpServletRequest request) throws Exception{
        textServiceImpl.begin();
    }

    @PostMapping(value = {"/movieTitleBegin"})
    public void movieTitleBegin(HttpServletRequest request) throws Exception{
        resourceMovieTitleServiceImpl.begin();
    }

}
