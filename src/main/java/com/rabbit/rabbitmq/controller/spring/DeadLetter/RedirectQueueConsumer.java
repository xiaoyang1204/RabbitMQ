package com.rabbit.rabbitmq.controller.spring.DeadLetter;

import com.rabbit.rabbitmq.config.rabbitmq.DeadLetter.RabbitDeadLetterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xingchongyang
 * 队列"死信"后，会将消息投递到Dead Letter Exchanges，然后该Exchange会将消息投递到重定向队列。
 */
@Component
@Slf4j
@RabbitListener(queues = RabbitDeadLetterConfig.REDIRECT_QUEUE)
public class RedirectQueueConsumer {


    /**
     * 重定向队列和死信队列形参一致Integer number
     * @param number
     */
    @RabbitHandler
    public void fromDeadLetter(Integer number){
        log.warn("RedirectQueueConsumer : {}", number);
        // 对应的操作
        int i = number / 1;
    }
}
