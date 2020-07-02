package com.rabbit.rabbitmq.primeval.controller;

import com.alibaba.fastjson.JSON;
import com.rabbit.rabbitmq.primeval.message.characteristic.deadLetter.DeadLetterConsumer;
import com.rabbit.rabbitmq.primeval.message.characteristic.deadLetter.DeadLetterProduction;
import com.rabbit.rabbitmq.primeval.message.characteristic.direct.consumer.DirectConsumer;
import com.rabbit.rabbitmq.primeval.message.characteristic.direct.production.DirectProduction;
import com.rabbit.rabbitmq.primeval.message.characteristic.work.WorkConsumer1;
import com.rabbit.rabbitmq.primeval.message.characteristic.work.WorkConsumer2;
import com.rabbit.rabbitmq.primeval.message.characteristic.work.WorkProduction;
import com.rabbit.rabbitmq.primeval.message.characteristic.workfair.WorkFairProduction;
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
 * 模式的API
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@RestController
public class RabbitMqController {

    //api 如:localhost:8080/hello

//    @GetMapping("/hello")
    @Test
    public String hello(){
        return JSON.toJSONString("hello");
    }

    //----------------------------------direct直连模式-------------------------------------------

//    @GetMapping(value = "/push/direct")
    @Test
    public void DirectPush() throws IOException, TimeoutException {
        DirectProduction.directConnection();
    }

//    @GetMapping(value = "/receive/direct")
    @Test
    public void DirectC() throws IOException, TimeoutException {
        DirectConsumer.getDirectConsumer();
    }

    //----------------------------------work工作者模式(多劳多得)-------------------------------------------

//    @GetMapping(value = "/receive/workPush")
    @Test
    public void workPush() throws IOException, TimeoutException, InterruptedException {
        WorkProduction.pushWorkProduction();
    }

//    @GetMapping(value = "/receive/workConsumer1")
    @Test
    public void workConsumer1() throws IOException, TimeoutException {
        WorkConsumer1.getWorkConsumer1();
    }

//    @GetMapping(value = "/receive/workConsumer2")
    @Test
    public void workConsumer2() throws IOException, TimeoutException {
        WorkConsumer2.getWorkConsumer1();
    }


    //----------------------------------work公平分发FairDispath-------------------------------------------

//    @GetMapping(value = "/receive/workFairPush")
    @Test
    public void workFairPush() throws IOException, TimeoutException, InterruptedException {
        WorkFairProduction.pushWorkProduction();
    }

//    @GetMapping(value = "/receive/workFairConsumer1")
    @Test
    public void workFairConsumer1() throws IOException, TimeoutException, InterruptedException {
        WorkFairProduction.pushWorkProduction();
    }

//    @GetMapping(value = "/receive/workFairConsumer2")
    @Test
    public void workFairConsumer2() throws IOException, TimeoutException, InterruptedException {
        WorkFairProduction.pushWorkProduction();
    }


    //===========================================死信队列==========================================

//    @GetMapping(value = "/receive/deadLetterPush")
    @Test
    public void deadLetterPush() throws IOException, TimeoutException, InterruptedException {
        DeadLetterProduction.send();
    }

//    @GetMapping(value = "/receive/deadLetterConsumer")
    @Test
    public void deadLetterConsumer() throws IOException, TimeoutException, InterruptedException {
        DeadLetterConsumer.receive();
    }
}
