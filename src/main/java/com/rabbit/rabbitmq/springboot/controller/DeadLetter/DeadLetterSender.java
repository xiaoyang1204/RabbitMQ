package com.rabbit.rabbitmq.springboot.controller.DeadLetter;

import com.rabbit.rabbitmq.springboot.config.rabbitmq.deadLetter.RabbitDeadLetterConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xingchongyang
 * 直接向死信队列里投递
 */
@Component
public class DeadLetterSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(int number){
        // 这里的Exchange可以是业务的Exchange，为了方便测试这里直接往死信Exchange里投递消息
        rabbitTemplate.convertAndSend(RabbitDeadLetterConfig.DEAD_LETTER_EXCHANGE,RabbitDeadLetterConfig.DEAD_LETTER_TEST_ROUTING_KEY,number);
    }
}
