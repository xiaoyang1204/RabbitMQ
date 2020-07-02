package com.rabbit.rabbitmq.spring.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

public class TestMessageConverter implements MessageConverter {

    /**
     * java对象转换成message形式
     * @param object
     * @param messageProperties
     * @return
     * @throws MessageConversionException
     */
    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        return new Message(object.toString().getBytes(),messageProperties);
    }

    /**
     * message对象转换成java形式
     * @param message
     * @return
     * @throws MessageConversionException
     */
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        String consumerTag = message.getMessageProperties().getConsumerTag();
        if(null != consumerTag && consumerTag.contains("text")){
            return new String(message.getBody());
        }
        return message.getBody();
    }
}
