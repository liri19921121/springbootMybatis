package com.common.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class producer {
    private final static Logger logger = LoggerFactory.getLogger(producer.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 发送消息
     * @param destination 发送到的队列
     * @param message 待发送信息
     */
    public void sendMessage(Destination destination, String user){
        logger.info("发送消息：{}",user);
        jmsTemplate.convertAndSend(destination,user);
    }

    public void sendMessage2(Destination destination, String message){
        logger.info("发送消息：{}",message);
        jmsTemplate.convertAndSend(destination,message);
    }
}
