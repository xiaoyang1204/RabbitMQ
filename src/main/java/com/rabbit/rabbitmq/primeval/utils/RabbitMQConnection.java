package com.rabbit.rabbitmq.primeval.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 */
public class RabbitMQConnection {


    public static Connection getConnection(){
        try {
            ConnectionFactory faction = new ConnectionFactory();
            faction.setUsername("test");
            faction.setPassword("test");
            faction.setVirtualHost("/");
            faction.setPort(5672);
            faction.setHost("49.235.193.4");
            return faction.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
