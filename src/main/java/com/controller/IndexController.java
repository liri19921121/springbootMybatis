package com.controller;

import com.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/index"})
public class IndexController {


    @Autowired
    private MessageServiceImpl messageService;

    @RequestMapping(value = {"/love"})
    public String setRedisData(HttpServletRequest request){
        return "love";
    }

    @RequestMapping(value = {"/message"})
    @ResponseBody
    public String Message(HttpServletRequest request){
        messageService.add();
        return "success";
    }

}
