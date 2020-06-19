package com.rabbit.rabbitmq.RabbitMqMessage.work;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author xingchongyang
 * 工作者模式
 */
public class WorkProduction {

    private final String WORK_QUEUE_NAME = "work_queue_name";

    public String pushWorkProduction() throws IOException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(WORK_QUEUE_NAME,false,false,false,null);
        //同一个服务器发送给一个消费者
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String smg = new String(body,"utf-8");
            }
        };

        return null;
    }
}
