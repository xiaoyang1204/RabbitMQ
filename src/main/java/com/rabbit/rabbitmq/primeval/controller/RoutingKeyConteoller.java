package com.rabbit.rabbitmq.primeval.controller;

import com.rabbit.rabbitmq.primeval.message.characteristic.routing.RoutingConsumer;
import com.rabbit.rabbitmq.primeval.message.characteristic.routing.RoutingConsumer2;
import com.rabbit.rabbitmq.primeval.message.characteristic.routing.RoutingProduction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 * RoutingKey
 */
@RestController
@RequestMapping("/routing")
public class RoutingKeyConteoller {

    @GetMapping("/send")
    public void send() throws IOException, TimeoutException {
        RoutingProduction.send();
    }

    @GetMapping("/receive")
    public void receive() throws IOException, TimeoutException {
        RoutingConsumer.receive();
    }

    @GetMapping("/receive2")
    public void receive2() throws IOException, TimeoutException {
        RoutingConsumer2.receive();
    }
}