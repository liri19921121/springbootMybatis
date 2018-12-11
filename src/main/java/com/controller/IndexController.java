package com.controller;

import com.mapper.MessageMapper2;
import com.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = {"/index"})
public class IndexController {

    @Autowired
    private MessageMapper2 messageMapper;

    @GetMapping(value = {"/love"})
    public String setRedisData(HttpServletRequest request){
        List<Message> list = messageMapper.selectAll();
        request.setAttribute("messageList",list);
        System.out.println(list.size());
        return "love";
    }

}
