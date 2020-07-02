package com.rabbit.rabbitmq.springboot.config.rabbitmq.message.reply;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author xingchongyang
 * 对应的手动确认消息监听类，MyAckReceiver.java（手动确认模式需要实现 ChannelAwareMessageListener）
 */
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String smg = message.toString();
            //因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
            //可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
            String[] split = smg.split("'");


            Map<String,Object> parse = (Map)JSON.parse(split[1].trim());

            System.out.println("  MyAckReceiver  messageId:"+parse.get("messageId")+"  messageData:"+ parse.get("messageData")+"  createTime:"+ parse.get("createTime"));
            System.out.println("消费的主题消息来自："+message.getMessageProperties().getConsumerQueue());
            channel.basicAck(deliveryTag, true);
            //为true会重新放回队列
//		channel.basicReject(deliveryTag, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
