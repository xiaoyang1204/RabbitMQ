package com.rabbit.rabbitmq.springboot.controller.DeadLetter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RunWith(SpringRunner.class)
@SpringBootTest
//@RestController
//@RequestMapping("/dead/letter")
public class DeadLetterController {


    @Autowired
    private DeadLetterSender deadLetterSender;


//    @GetMapping("/send")
    @Test
    public void sendDeadLetterQueue(){
        deadLetterSender.send(15);
    }


}
