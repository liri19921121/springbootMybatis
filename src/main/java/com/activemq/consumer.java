package com.activemq;

import com.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class consumer {
    private final static Logger logger = LoggerFactory.getLogger(consumer.class);

    @JmsListener(destination = "springboot.queue.test2")
    public void receiveQueue( UserInfo user) {
        logger.info("接收到消息：{}",user.getUsername()+user.getSex());
    }

}
