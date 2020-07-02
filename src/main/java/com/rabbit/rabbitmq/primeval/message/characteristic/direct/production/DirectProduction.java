package com.rabbit.rabbitmq.primeval.message.characteristic.direct.production;

import com.rabbit.rabbitmq.primeval.utils.RabbitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xingchongyang
 * 直连
 */
@Slf4j
public class DirectProduction {

    //队列名称
    private final static String QUEUE_NAME;

    static {
        QUEUE_NAME = "q_test_01";
    }

    public static void directConnection() throws IOException, TimeoutException {
        //获取链接
        Connection connectino = RabbitMQConnection.getConnection();
        //创建队列
        Channel channel = connectino.createChannel();
        ///声明队列
        /**
         *  durable 是否持久化
         *    如果已经生成的队列名称 WORK_QUEUE_NAME ，将false 还成true 是不可以的，尽管代码是正确的，也不会运行成功的。
         *    RabbitMQ 不准许重新定义（不同参数）已存在的队列。
         *    解决方案，删除这个队列，或者定义不同名称的队列。
         */
        Boolean durable = false;
        channel.queueDeclare(QUEUE_NAME,durable,false,false,null);
        //消息内容
        String message = "hello rabbitMQ Direct";
        //发送消息
        //exchang为空 就是匿名发送
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

//        log.info(message);
        //关闭通道和连接
        channel.close();
        connectino.close();
    }
}
