package com.rabbit.rabbitmq.primeval.message.characteristic.deadLetter;

import com.rabbit.rabbitmq.primeval.utils.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeadLetterConsumer {

    /**
     * 普通的交换机和队列及路由
     */
    private final static String TEST_DLX_EXCHANGE = "test_dlx_exchange";
    private final static String TEST_QUEUE_NAME = "text_dlx_queue";

    //死信队列的exchange
    private final static String DLX_EXCHANGE = "dlx.exchange";
    private final static String QUEUE_NAME = "dlx.queue";


    public static void receive() throws IOException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        String routingKey = "dlx.#";

        channel.exchangeDeclare(TEST_DLX_EXCHANGE,"topic",true,false,null);
        //死信队列的声明,发送到指定的exchange上
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange",DLX_EXCHANGE);
        channel.queueDeclare(TEST_QUEUE_NAME,true,false,false,arguments);
        channel.queueBind(TEST_QUEUE_NAME,TEST_DLX_EXCHANGE,routingKey);

        //死信队列的声明
        channel.exchangeDeclare(DLX_EXCHANGE,"topic",true,false,null);
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //#所有的键，都路由到这个队列
        channel.queueBind(QUEUE_NAME,DLX_EXCHANGE,"#");
        channel.basicConsume(TEST_QUEUE_NAME,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body,"utf-8"));
            }
        });

    }
}
