package com.rabbit.rabbitmq.RabbitMqMessage.consumer;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;

/**
 * @author xingchongyang
 */
public class DirectConsumer {

    //队列名称
    private final static String QUEUE_NAME;

    static {
        QUEUE_NAME = "q_test_01";
    }

    private static void getDirectConsumer() throws IOException {
        // 获取到连接以及mq通道
        Connection connectino = RabbitMQConnection.getConnection();
        // 从连接中创建通道
        Channel channel = connectino.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel);
        //监听队列


    }
}
