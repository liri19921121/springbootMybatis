package com.service.impl;

import com.common.BaseService.BaseService;
import com.pojo.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl extends BaseService<Message> {

    public void add() {
        Message message = new Message();
        message.setMessage("ceshi");
        add(message);
    }

}
