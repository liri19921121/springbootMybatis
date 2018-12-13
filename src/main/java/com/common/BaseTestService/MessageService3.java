package com.common.BaseTestService;

import com.common.BaseTestService.MessageMapper3;
import com.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService3 {

    @Autowired
    private MessageMapper3 messageMapper;

    public Message getMessage(Long id){
        return this.messageMapper.selectByPrimaryKey(id);
    }

}
