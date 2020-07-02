package com.rabbit.rabbitmq.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xingchongyang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Test
    public void get(){
        System.out.println("==========================");
    }

    @Test
    public void testAdmin(){
        rabbitAdmin.declareExchange(new DirectExchange("test.direct.exchange",false,false));
        rabbitAdmin.declareQueue(new Queue("test.direct.queue",false));
        //Binding.DestinationType.QUEUE 对于绑定的QUEUE
//        rabbitAdmin.declareBinding(new Binding("test.direct.queue",Binding.DestinationType.QUEUE,"test.direct","direct",new HashMap<>()));
        /**
         * 使用链式变成的方式
         * BindingBuilder.bind()构建绑定
         * 再次new Queue 不会重新正在次创建
         * 如果是fanout 是没有路由键的
         */
        rabbitAdmin.declareBinding(
                BindingBuilder
                        //直接创建队列
                .bind(new Queue("test.direct.queue",false))
                        //直接创建交换机绑定关系
                .to(new DirectExchange("test.direct.exchange",false,false))
                        //指定路由routingKey
                .with("user.#"));

        //清除给定队列的内容
        rabbitAdmin.purgeQueue("test.direct.queue",false);
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage(){
        //创建消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("desc","信息描述");
        messageProperties.getHeaders().put("type","自定义消息类型....");
        Message message = new Message("Hello RabbitMQ".getBytes(),messageProperties);
        //发送消息
        rabbitTemplate.convertAndSend("exchange001", "spring.123", message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.err.println("------添加额外的设置-------");
                message.getMessageProperties().getHeaders().put("attr","额外新加的属性");
                return message;
            }
        });
    }

    /**
     * 简化方式
     */
    @Test
    public void testSendMessage2(){
        //发送消息
        rabbitTemplate.convertAndSend("exchange001", "spring.456", "我是一条消息");
    }
}
