package com.rabbit.rabbitmq.controller;

import com.alibaba.fastjson.JSON;
import com.rabbit.rabbitmq.RabbitMqMessage.direct.consumer.DirectConsumer;
import com.rabbit.rabbitmq.RabbitMqMessage.direct.production.DirectProduction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 */
@RestController
public class RabbitMqController {

    @GetMapping("/hello")
    public String hello(){
        return JSON.toJSONString("hello");
    }


    @GetMapping(value = "/push/direct")
    public void DirectPush() throws IOException, TimeoutException {
        DirectProduction.directConnection();
    }

    @GetMapping(value = "/receive/direct")
    public void DirectC() throws IOException, TimeoutException {
        DirectConsumer.getDirectConsumer();
    }

}
