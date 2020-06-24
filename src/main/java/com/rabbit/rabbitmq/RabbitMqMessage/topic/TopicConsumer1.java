package com.rabbit.rabbitmq.RabbitMqMessage.topic;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;

public class TopicConsumer1 {


    private final static String EXCHANGE_TOPIC = "tets.exchange.topic";
    private final static String QUEUE_TOPIC = "tets.queue.topic";

    public static void receive() throws IOException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_TOPIC,false,false,false,null);
        channel.queueBind(QUEUE_TOPIC,EXCHANGE_TOPIC,"goods.add");//goods.#
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String smg = new String(body,"utf-8");
                System.out.println(smg+"[1]");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        channel.basicConsume(QUEUE_TOPIC,false,consumer);
    }
}
