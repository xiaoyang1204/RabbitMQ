package com.rabbit.rabbitmq.primeval.controller;

import com.rabbit.rabbitmq.primeval.message.characteristic.routing.RoutingConsumer;
import com.rabbit.rabbitmq.primeval.message.characteristic.routing.RoutingConsumer2;
import com.rabbit.rabbitmq.primeval.message.characteristic.routing.RoutingProduction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 * RoutingKey
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@RestController
//@RequestMapping("/routing")
public class RoutingKeyConteoller {

//    @GetMapping("/send")
    @Test
    public void send() throws IOException, TimeoutException {
        RoutingProduction.send();
    }

//    @GetMapping("/receive")
    @Test
    public void receive() throws IOException, TimeoutException {
        RoutingConsumer.receive();
    }

//    @GetMapping("/receive2")
    @Test
    public void receive2() throws IOException, TimeoutException {
        RoutingConsumer2.receive();
    }
}
