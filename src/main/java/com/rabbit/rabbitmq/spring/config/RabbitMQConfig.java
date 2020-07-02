package com.rabbit.rabbitmq.spring.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xingchongyang
 * spring整合RabbitMQ
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    CachingConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUsername("test");
        factory.setPassword("test");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        factory.setHost("49.235.193.4");
        return factory;
    }

    /**
     * RabbitAdmin底层实现就是从Spring容器中获取Exchange、Binding、RoutingKey以及Queue的Bean声明的
     * @param cachingConnectionFactory
     * @return
     */
    @Bean
    RabbitAdmin rabbitAdmin(CachingConnectionFactory cachingConnectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(cachingConnectionFactory);
        //AutoStartup必须设置为true,否者Spring容器不会加载RabbitAdmin类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    /**
     * 和Spring整合需要实例化RabbitMQ,与SpringBoot整合只需要在yml里配置相应信息即可
     * @return
     */
    @Bean
    RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        return rabbitTemplate;
    }

    /**
     * 声明消息
     * @return
     */
    @Bean
    Queue topic001(){
        return new Queue("topic001",true);
    }

    /**
     * 声明Exchange如： TopicExchange , fanoutExchange , directExchange
     * @return
     */
    @Bean
    TopicExchange exchange001(){
        return new TopicExchange("exchange001",true,false);
    }

    /**
     * 绑定
     * @return
     */
    @Bean
    Binding binding001(){
        return BindingBuilder.bind(topic001()).to(exchange001()).with("spring.*");
    }

    /**
     * 如果topic001()消息 要吧springboot文件夹下的message中的MessageListenerConfig 中的Configuration 注释掉，会有冲突，
     * 相反启动笼另外一个注释这个
     * 监听
     */
    @Bean
    SimpleMessageListenerContainer simpleMessageListenerContainer(CachingConnectionFactory cachingConnectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        //监听的队列
        container.setQueues(topic001());
        //并发使用者数
        container.setConcurrentConsumers(1);
        //消费者数量上限
        container.setMaxConcurrentConsumers(2);
        //是否重回队列
        container.setDefaultRequeueRejected(false);
        //应答方式自动应答
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        //消费端的标签策略
        container.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String queue) {
                return queue + "_" + UUID.randomUUID().toString();
            }
        });
        //监听消息
       container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String smg = new String(message.getBody());
                System.err.println("消费者-------------" + smg);
            }
        });

        /**
         * 1.消息监听适配器 自定义
         *
         */
     /* MessageListenerAdapter messageListenerAdapter  = new MessageListenerAdapter(new MessageDelegate());
        //可修改自定义的方法名
        messageListenerAdapter.setDefaultListenerMethod("consumerMessage");
        messageListenerAdapter.setMessageConverter(new TestMessageConverter());
        container.setMessageListener(messageListenerAdapter);
         */
        /**
         * 2.消息监听适配器方式：我们队列名称和方法名称也可以进行一一的匹配
         */
        /*
        MessageListenerAdapter messageListenerAdapter  = new MessageListenerAdapter(new MessageDelegate());
        Map<String,String> map = new HashMap<>();
        map.put("topic001","consumerMessage");
        messageListenerAdapter.setQueueOrTagToMethodName(map);
        container.setMessageListener(messageListenerAdapter);
         */
        return container;
    }
}
