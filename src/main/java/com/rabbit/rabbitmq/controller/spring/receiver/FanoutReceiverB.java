package com.rabbit.rabbitmq.controller.spring.receiver;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xingchongyang
 */
@Component
@RabbitListener(queues = "queueB")
public class FanoutReceiverB {

    @RabbitHandler
    public void process(String smg){
        Map parse = (Map)JSON.parse(smg);
        System.out.println("FanoutReceiverB消费者收到消息"+parse);
    }
}
