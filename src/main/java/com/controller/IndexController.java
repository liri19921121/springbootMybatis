package com.controller;

import com.pojo.Message;
import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/"})
public class IndexController {

    @Autowired
    private MessageService messageService;

    /**
     * 首页
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = {"/"})
    public String setRedisData(HttpServletRequest request) throws Exception{
        List<Message> list = messageService.getByConditionList();
        request.setAttribute("messageList",list);
        return "love";
    }

    @PostMapping(value = {"/add-message"})
    @ResponseBody
    public String addMessage(String message,String name) throws Exception{
        return messageService.addMessage(message,name);
    }

    @GetMapping(value = {"/stripe"})
    public String stripe(HttpServletRequest request) throws Exception{
        return "stripe";
    }

}
