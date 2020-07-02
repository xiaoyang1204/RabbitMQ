package com.rabbit.rabbitmq.springboot.controller.send;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xingchongyang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@RestController
//@RequestMapping("/spring")
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;

//    @GetMapping("/sendDirectMessage")
    @Test
    public String sendDirectMessage(){
        String smg = getMessage("hello Spring RabbitMQ");
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting",smg);
        return "ok";
    }

    /**
     * TopicManReceiver监听队列1，绑定键为：topic.man
     * TopicTotalReceiver监听队列2，绑定键为：topic.#
     * 而当前推送的消息，携带的路由键为：topic.woman
     *
     * 所以可以看到两个监听消费者只有TopicTotalReceiver成功消费到了消息。
     * @return
     */
//    @GetMapping("/sendTopicMessage1")
    @Test
    public String sendTopicMessage1(){
        String smg = getMessage("message: M A M");
        rabbitTemplate.convertAndSend("topicExchange","topic.man",smg);
        return "ok";
    }

//    @GetMapping("/sendTopicMessage2")
    @Test
    public String sendTopicMessage2(){
        String smg = getMessage("message: W O M A M");
        rabbitTemplate.convertAndSend("topicExchange","topic.woman",smg);
        return "ok";
    }

    /**
     * 只要发送到 fanoutExchange 这个扇型交换机的消息， 三个队列都绑定这个交换机，所以三个消息接收类都监听到了这条消息。
     * @return
     */
//    @GetMapping("/sendFanoutMessage")
    @Test
    public String sendFanoutMessage(){
        String smg = getMessage("message : testFanoutMessage");
        rabbitTemplate.convertAndSend("fanoutExchange",null,smg);
        return "ok";
    }

    /**
     * ①这种情况触发的是 ConfirmCallback 回调函数。
     * @return
     */
//    @GetMapping("/TestMessageAck")
    @Test
    public String TestMessageAck(){
        String message = getMessage("message: non-existent-exchange test message ");
        rabbitTemplate.convertAndSend("non-existent-exchange","TestDirectRouting",message);
        return "ok";
    }

    /**
     * ②这种情况触发的是 ConfirmCallback和RetrunCallback两个回调函数。
     * @return
     */
//    @GetMapping("/TestMessageAck2")
    @Test
    public String TestMessageAck2() {
        String message = getMessage("message: lonelyDirectExchange test message ");
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", message);
        return "ok";
    }

    String getMessage(String message){
        String messageId = UUID.randomUUID().toString().replace("-","");
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("messageId",messageId);
        map.put("message",message);
        map.put("createTime",createTime);
        return JSON.toJSONString(map);
    }
}
