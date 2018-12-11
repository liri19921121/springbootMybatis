package com.service.impl;

import com.common.BaseService.BaseService2;
import com.pojo.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl extends BaseService2<Message> {

    public void add() {
        Message message = new Message();
        message.setMessage("ceshi");
        add(message);
    }

}
