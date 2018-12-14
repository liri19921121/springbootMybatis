package com.controller;

import com.service.impl.SpiderServiceImpl;
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

    @PostMapping(value = {"/begin"})
    public void begin(HttpServletRequest request) throws Exception{
        spiderServiceImpl.begin();
    }
}
