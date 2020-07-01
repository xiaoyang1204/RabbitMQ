package com.rabbit.rabbitmq.controller.spring.DeadLetter;

import com.rabbit.rabbitmq.config.rabbitmq.deadLetter.RabbitDeadLetterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author xingchongyang
 * 这里会抛异常
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitDeadLetterConfig.DEAD_LETTER_QUEUE,containerFactory="rabbitListenerContainerFactory")
public class DeadLetterConsumer {

    @RabbitHandler
    public void testDeadLetterQueueAndThrowsException(@Payload Integer number){
        log.warn("DeadLetterConsumer :{}/0 ----------", number);
        int i = number / 0;
    }
}
