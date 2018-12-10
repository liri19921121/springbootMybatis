package com.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumer3 {
    private final static Logger logger = LoggerFactory.getLogger(JMSConsumer3.class);

    /**
     * 订阅消费者1号
     * @param msg
     */
    @JmsListener(destination = JmsConfig.TOPIC,containerFactory = "jmsListenerContainerTopic")
    public void onTopicMessage(String msg) {
        logger.info("onTopicMessage1接收到topic消息：{}",msg);
    }

    /**
     * 订阅消费者2号
     * @param msg
     */
    @JmsListener(destination = JmsConfig.TOPIC,containerFactory = "jmsListenerContainerTopic")
    public void onTopicMessage2(String msg) {
        logger.info("onTopicMessage2接收到topic消息：{}",msg);
    }

    /**
     * 队列消费者1号
     * @param msg
     */
    @JmsListener(destination = JmsConfig.QUEUE,containerFactory = "jmsListenerContainerQueue")
    public void onQueueMessage(String msg) {
        logger.info("onQueueMessage1接收到queue消息：{}",msg);
    }

    /**
     * 队列消费者2号
     * @param msg
     */
    @JmsListener(destination = JmsConfig.QUEUE,containerFactory = "jmsListenerContainerQueue")
    public void onQueueMessage2(String msg) {
        logger.info("onQueueMessage2接收到queue消息：{}",msg);
    }
}
