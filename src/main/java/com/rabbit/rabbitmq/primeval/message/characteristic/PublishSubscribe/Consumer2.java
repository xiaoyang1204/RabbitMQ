package com.rabbit.rabbitmq.primeval.message.characteristic.PublishSubscribe;

import com.rabbit.rabbitmq.primeval.utils.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {

    private static final String QUEUE_NAME = "test.queue.fanout.emali";
    private static final String EXCHANGE_NAME = "test.exchange.fanout";

    public static void  consumer() throws IOException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        //队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String smg = new String(body,"utf-8");
                System.out.println(smg+"===[2]");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);

    }
}
