package com.rabbit.rabbitmq.springboot.config.rabbitmq.message.reply;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingchongyang
 * 手动确认消息配置
 */
//@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    //消息接收处理类
    @Autowired
    private MyAckReceiver myAckReceiver;

    /**
     * 简单消息监听容器
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(cachingConnectionFactory);
        //设置当前的消费者数量
        simpleMessageListenerContainer.setConcurrentConsumers(1);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(1);
        //RabbitMQ默认是自动确认，这里改为手动确认消息
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置一个队列
        simpleMessageListenerContainer.setQueueNames("TestDirectQueue");
        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
        //  container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");
        //同时监听多个队列
//        simpleMessageListenerContainer.setQueues(queue001(), queue002(), queue003(), queue_image(), queue_pdf());
        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        //container.setQueues(new Queue("TestDirectQueue",true));
        //container.addQueues(new Queue("TestDirectQueue2",true));
        //container.addQueues(new Queue("TestDirectQueue3",true));
        //设置消息监听
        simpleMessageListenerContainer.setMessageListener(myAckReceiver);
        return simpleMessageListenerContainer;
    }


}
