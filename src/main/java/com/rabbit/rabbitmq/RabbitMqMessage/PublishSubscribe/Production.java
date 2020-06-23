package com.rabbit.rabbitmq.RabbitMqMessage.PublishSubscribe;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 * 发送者
 *
 * 交换机没有存储的能力,在rabbitMq里面只有队列存储能力，因为这时候还没有队列绑定到这个交换机，所以丢失数据
 */
public class Production {
    //定义交换机名称
    private static final String EXCHANGE_NAME = "test.exchange.fanout";

    public static void  getSend() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//分发fanout

        //发送消息
        String smg = "hello publishSubscribe";

        channel.basicPublish(EXCHANGE_NAME,"",null,smg.getBytes());

        System.out.println("send" + smg);
        channel.close();
        connection.close();

    }
}
