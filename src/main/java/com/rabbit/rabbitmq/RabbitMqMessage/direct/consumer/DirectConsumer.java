package com.rabbit.rabbitmq.RabbitMqMessage.direct.consumer;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author xingchongyang
 * 直连
 */
public class DirectConsumer {

    //队列名称
    private final static String QUEUE_NAME;

    static {
        QUEUE_NAME = "q_test_01";
    }

    public static void getDirectConsumer() throws IOException {
        // 获取到连接以及mq通道
        Connection connectino = RabbitMQConnection.getConnection();
        // 从连接中创建通道
        Channel channel = connectino.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            //获取消息到达
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String smg = new String(body,"UTF-8");
                System.out.println(smg);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
