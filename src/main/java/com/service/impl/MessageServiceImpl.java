package com.service.impl;

import com.mapper.MessageMapper;
import com.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements com.service.MessageService {

    @Autowired
    MessageMapper messageMapper;

    public List<Message> getByConditionList() throws Exception {
        Example example = new Example(Message.class);
        example.orderBy("time").desc();
        List<Message> list = messageMapper.selectByExample(example);
        return list;

    }

    public String addMessage(String message,String name) throws Exception{
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
