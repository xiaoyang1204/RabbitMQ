package com.rabbit.rabbitmq.controller;

import com.alibaba.fastjson.JSON;
import com.rabbit.rabbitmq.RabbitMqMessage.direct.consumer.DirectConsumer;
import com.rabbit.rabbitmq.RabbitMqMessage.direct.production.DirectProduction;
import com.rabbit.rabbitmq.RabbitMqMessage.work.WorkConsumer1;
import com.rabbit.rabbitmq.RabbitMqMessage.work.WorkConsumer2;
import com.rabbit.rabbitmq.RabbitMqMessage.work.WorkProduction;
import com.rabbit.rabbitmq.RabbitMqMessage.workfair.WorkFairProduction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 */
@RestController
public class RabbitMqController {

    //api 如:localhost:8080/hello

    @GetMapping("/hello")
    public String hello(){
        return JSON.toJSONString("hello");
    }

    //----------------------------------direct直连模式-------------------------------------------

    @GetMapping(value = "/push/direct")
    public void DirectPush() throws IOException, TimeoutException {
        DirectProduction.directConnection();
    }

    @GetMapping(value = "/receive/direct")
    public void DirectC() throws IOException, TimeoutException {
        DirectConsumer.getDirectConsumer();
    }

    //----------------------------------work工作者模式(多劳多得)-------------------------------------------

    @GetMapping(value = "/receive/workPush")
    public void workPush() throws IOException, TimeoutException, InterruptedException {
        WorkProduction.pushWorkProduction();
    }

    @GetMapping(value = "/receive/workConsumer1")
    public void workConsumer1() throws IOException, TimeoutException {
        WorkConsumer1.getWorkConsumer1();
    }

    @GetMapping(value = "/receive/workConsumer2")
    public void workConsumer2() throws IOException, TimeoutException {
        WorkConsumer2.getWorkConsumer1();
    }


    //----------------------------------work公平分发FairDispath-------------------------------------------

    @GetMapping(value = "/receive/workFairPush")
    public void workFairPush() throws IOException, TimeoutException, InterruptedException {
        WorkFairProduction.pushWorkProduction();
    }

    @GetMapping(value = "/receive/workFairConsumer1")
    public void workFairConsumer1() throws IOException, TimeoutException, InterruptedException {
        WorkFairProduction.pushWorkProduction();
    }

    @GetMapping(value = "/receive/workFairConsumer2")
    public void workFairConsumer2() throws IOException, TimeoutException, InterruptedException {
        WorkFairProduction.pushWorkProduction();
    }

}
