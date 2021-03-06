package com.rabbit.rabbitmq.springboot.controller.receiver;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xingchongyang
 */
@Component
@RabbitListener(queues = "topic.woman")
public class TopicManReceiver2 {

    @RabbitHandler
    public void process(String smg){
        Map parse = (Map)JSON.parse(smg);
        System.out.println("TopicManReceiver消费者收到消息[2]  : " + parse);
    }

}
