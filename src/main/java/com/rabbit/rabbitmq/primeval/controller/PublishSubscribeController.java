package com.rabbit.rabbitmq.primeval.controller;

import com.rabbit.rabbitmq.primeval.message.characteristic.PublishSubscribe.Consumer1;
import com.rabbit.rabbitmq.primeval.message.characteristic.PublishSubscribe.Consumer2;
import com.rabbit.rabbitmq.primeval.message.characteristic.PublishSubscribe.Production;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 * 发布订阅
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@RestController
public class PublishSubscribeController {


//    @GetMapping(value = "/send")
    @Test
    public void send() throws IOException, TimeoutException {
        Production.getSend();
    }


//    @GetMapping(value = "/consumer1")
    @Test
    public void consumer1() throws IOException, TimeoutException {
        Consumer1.consumer();
    }

//    @GetMapping(value = "/consumer2")
    @Test
    public void consumer2() throws IOException, TimeoutException {
        Consumer2.consumer();
    }


}
