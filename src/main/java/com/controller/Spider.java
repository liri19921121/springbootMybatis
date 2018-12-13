package com.controller;

import com.pojo.Message;
import com.service.MovieResourcesService;
import com.service.impl.SpiderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/spider"})
public class Spider {

    @Autowired
    private SpiderServiceImpl spiderService;

    @PostMapping(value = {"/begin"})
    public void begin(HttpServletRequest request) throws Exception{
        spiderService.begin();
    }
}
