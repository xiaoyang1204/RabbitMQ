package com.rabbit.rabbitmq.springboot.controller.receiver;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xingchongyang
 * 监听队列
 */
@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiver {

    @RabbitHandler
    public void process(String smg){
        Map parse = (Map)JSONObject.parse(smg);
        System.out.println(parse.toString());
    }
}
