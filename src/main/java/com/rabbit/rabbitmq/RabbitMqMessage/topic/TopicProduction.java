package com.rabbit.rabbitmq.RabbitMqMessage.topic;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 */
public class TopicProduction {
    /**
     *  topic exchange
     *  将路由键和某个模式匹配
     *  # 匹配一个或者多个
     *  * 匹配一个
     */

    private final static String EXCHANGE_TOPIC = "tets.exchange.topic";

    public static void send() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_TOPIC,"topic");
        channel.basicQos(1);
        String smg = "hello topic";

        channel.basicPublish(EXCHANGE_TOPIC,"goods.add",null,smg.getBytes());
        channel.close();
        connection.close();
    }

}
