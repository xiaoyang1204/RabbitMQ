package com.rabbit.rabbitmq.primeval.message.characteristic.work;

import com.rabbit.rabbitmq.primeval.utils.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 * 工作者模式
 */
public class WorkProduction {

    private final static String WORK_QUEUE_NAME = "work_queue_name";

    public static String pushWorkProduction() throws IOException, InterruptedException, TimeoutException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(WORK_QUEUE_NAME,false,false,false,null);
        //同一个服务器发送给一个消费者
//        channel.basicQos(1);
        for (int i = 0; i < 50; i++) {
            String smg = "hello"+i;
            channel.basicPublish("",WORK_QUEUE_NAME,null,smg.getBytes());
            Thread.sleep(i*20);
        }

        channel.close();
        connection.close();
        return null;
    }
}
