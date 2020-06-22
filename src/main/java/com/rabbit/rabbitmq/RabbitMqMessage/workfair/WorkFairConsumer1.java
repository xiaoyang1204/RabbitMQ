package com.rabbit.rabbitmq.RabbitMqMessage.workfair;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author xingchongyang
 * 工作者模式（公平分发）
 */
public class WorkFairConsumer1 {

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
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        /**
         * autoAck 是否自动应答 ，true 自动应答，false 手动应答
         *  1.使用true自动应答，当RabbitMQ分发给消费者，就会从内存中删除。这种情况如果杀死正在这执行的消费者，就会丢失正在处理的消息。
         *  2.使用false 手动应答，如果一个消费者挂掉，就会交付给其他消费者，rabbitMq支持消息应答，消费者发送一个消息应答，告诉rabbitMq
         *  这个消息我已经应答可以删了，然后rabbitMQ 就删除内存中的消息。
         *  3.RabbitMQ默认自动应答
         */
        Boolean autoAck = false;
        channel.basicConsume(WORK_QUEUE_NAME,autoAck,consumer);
    }
}
