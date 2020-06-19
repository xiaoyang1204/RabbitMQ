package com.rabbit.rabbitmq.RabbitMqMessage.production;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 */
@Slf4j
public class DirectConnection {

    //队列名称
    private final static String QUEUE_NAME;

    static {
        QUEUE_NAME = "q_test_01";
    }

    public static void directConnection() throws IOException, TimeoutException {
        //获取链接
        Connection connectino = RabbitMQConnection.getConnection();
        //创建队列
        Channel channel = connectino.createChannel();
        ///声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消息内容
        String message = "hello rabbitMQ Direct";

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        log.info(message);
        //关闭通道和连接
        channel.close();
        connectino.close();
    }
}
