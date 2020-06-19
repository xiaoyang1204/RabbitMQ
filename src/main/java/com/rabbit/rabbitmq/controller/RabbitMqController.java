package com.rabbit.rabbitmq.controller;

import com.alibaba.fastjson.JSON;
import com.rabbit.rabbitmq.RabbitMqMessage.production.DirectConnection;
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


    @GetMapping(value = "/direct")
    public void getDirectConnection() throws IOException, TimeoutException {
        DirectConnection.directConnection();
    }

}
