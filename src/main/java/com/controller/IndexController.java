package com.controller;

import com.mapper.MessageMapper2;
import com.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = {"/"})
public class IndexController {

    @Autowired
    private MessageMapper2 messageMapper;

    @GetMapping(value = {"/"})
    public String setRedisData(HttpServletRequest request){
        List<Message> list = messageMapper.selectAll();
        request.setAttribute("messageList",list);
        System.out.println(list.size());
        return "love";
    }

    @PostMapping(value = {"/add-message"})
    @ResponseBody
    public String addMessage(String message,String name){
        Message mes = new Message();
        mes.setMessage(message);
        mes.setWriter(name);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = dateFormat.format(new Date());
        mes.setTime(now);
        messageMapper.insert(mes);
        return "success";
    }

}
