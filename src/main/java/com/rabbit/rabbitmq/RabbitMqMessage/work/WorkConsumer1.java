package com.rabbit.rabbitmq.RabbitMqMessage.work;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;

public class WorkConsumer1 {

    private final static String WORK_QUEUE_NAME = "work_queue_name";

    public static void getWorkConsumer1() throws IOException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(WORK_QUEUE_NAME,false,false,false,null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String smg = new String(body,"utf-8");
                System.out.println("[1]"+smg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("[done]");
                }
            }
        };
        channel.basicConsume(WORK_QUEUE_NAME,true,consumer);


    }
}
