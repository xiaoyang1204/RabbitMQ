package com.rabbit.rabbitmq.RabbitMqMessage.deadLetter;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 * 死信队列生产者
 */
public class DeadLetterProduction {

    private final static String TEST_DLX_EXCHANGE = "test_dlx_exchange";


    public static void send() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        String smg = "hello DeadLetter";
        String DLX_RUTING_KET = "dlx.save";
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)
                .contentEncoding("utf-8")
                .expiration("10000")
                .build();
        channel.basicPublish(TEST_DLX_EXCHANGE,DLX_RUTING_KET,false,properties,smg.getBytes());
        System.out.println("send"+smg);
        channel.close();
        connection.close();
    }


}
