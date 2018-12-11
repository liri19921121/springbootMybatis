package com.common.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

@RestController
@RequestMapping(value = {"/jms"})
public class test {

    @Autowired
    private producer producer;


    /**
     * 简单发送demo
     * @param id
     */
    @RequestMapping(value = {"/index"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public void testJms(Long id) {
        Destination destination = new ActiveMQQueue("springboot.queue.test2");
            producer.sendMessage(destination,"sss");
    }

    @Autowired
    private Topic topic;
    @Autowired
    private Queue queue;

    /**
     * 同时消费队列和发布订阅两类型消息
     */
    @RequestMapping(value = {"/testJms2"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public void testJms2() {
        for (int i = 0;i<6;i++){
            producer.sendMessage2(queue,"queue,world!" +i);
            producer.sendMessage2(topic, "topic,world!"+i);
        }
    }
}
