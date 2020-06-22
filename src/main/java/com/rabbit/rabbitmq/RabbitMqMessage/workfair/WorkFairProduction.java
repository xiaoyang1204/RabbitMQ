package com.rabbit.rabbitmq.RabbitMqMessage.workfair;

import com.rabbit.rabbitmq.utils.RabbitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 * 工作者模式（公平分发）
 */
public class WorkFairProduction {

    private final static String WORK_QUEUE_NAME = "work_queue_name";

    public static String pushWorkProduction() throws IOException, InterruptedException, TimeoutException {
        Connection connection = RabbitMQConnection.getConnection();
        Channel channel = connection.createChannel();

        /**
         *  durable 是否持久化
         *    如果已经生成的队列名称 WORK_QUEUE_NAME ，将flase 还成true 是不可以的，尽管代码是正确的，也不会运行成功的。
         *    RabbitMQ 不准许重新定义（不同参数）已存在的队列。
         *    解决方案，删除这个队列，或者定义不同名称的队列。
         */
        Boolean durable = false;
        channel.queueDeclare(WORK_QUEUE_NAME,durable,false,false,null);
        //同一个服务器发送给一个消费者
        channel.basicQos(1);
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
