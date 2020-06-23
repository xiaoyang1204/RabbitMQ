package com.rabbit.rabbitmq.controller;

import com.rabbit.rabbitmq.RabbitMqMessage.PublishSubscribe.Consumer1;
import com.rabbit.rabbitmq.RabbitMqMessage.PublishSubscribe.Consumer2;
import com.rabbit.rabbitmq.RabbitMqMessage.PublishSubscribe.Production;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class PublishSubscribeController {

    @GetMapping(value = "/send")
    public void send() throws IOException, TimeoutException {
        Production.getSend();
    }


    @GetMapping(value = "/consumer1")
    public void consumer1() throws IOException, TimeoutException {
        Consumer1.consumer();
    }

    @GetMapping(value = "/consumer2")
    public void consumer2() throws IOException, TimeoutException {
        Consumer2.consumer();
    }


}
