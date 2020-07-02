package com.rabbit.rabbitmq.spring.controller.DeadLetter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dead/letter")
public class DeadLetterController {


    @Autowired
    private DeadLetterSender deadLetterSender;


    @GetMapping("/send")
    public void sendDeadLetterQueue(){
        deadLetterSender.send(15);
    }


}
