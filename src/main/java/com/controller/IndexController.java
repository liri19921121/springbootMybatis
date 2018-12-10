package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/index"})
public class IndexController {

    @RequestMapping(value = {"/love"})
    public String setRedisData(HttpServletRequest request){
       /* List<Message> list = indexService.queryMessageList();
        request.setAttribute("messageList", list);*/
        return "love";
    }

}
