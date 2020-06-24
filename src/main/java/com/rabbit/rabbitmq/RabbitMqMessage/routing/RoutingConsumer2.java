package com.rabbit.rabbitmq.RabbitMqMessage.routing;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author xingchongyang
 * RoutingKey
 */
public class RoutingConsumer2 {

    private final static String QUEUE_NAME = "tets.queue_direct_2";
    private final static String EXCHANGE_NAME = "test.exchange.direct";

    public static void receive() throws IOException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"info");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"error");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String smg = new String(body,"utf-8");
                System.out.println(smg+"[2]");
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
