package com.service;

import com.mapper.MessageMapper;
import com.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    public Message getMessage(Long id){
        return this.messageMapper.selectByPrimaryKey(id);
    }

}
