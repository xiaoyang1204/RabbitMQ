package com.rabbit.rabbitmq.primeval.message.characteristic.routing;

import com.rabbit.rabbitmq.primeval.utils.RabbitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 * RoutingKey
 */
public class RoutingProduction {

    private final static String EXCHANGE_NAME = "test.exchange.direct";

    public static void send() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        String smg = "hello routing";
        String ROUTING_KEY = "error";
        channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY,null,smg.getBytes());
        System.out.println("send"+smg);
        channel.close();
        connection.close();
    }
}
