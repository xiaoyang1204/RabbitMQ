package com.rabbit.rabbitmq.spring.config;

public class MessageDelegate {

    //默认固定格式 handleMessage(byte[] messageBody)
//    public void handleMessage(byte[] messageBody){
//        System.err.println("MessageDelegate.handleMessage" + new String(messageBody));
//    }

    public void consumerMessage(byte[] messageBody){
        System.err.println("MessageDelegate.consumerMessage" + new String(messageBody));
    }

//
//    public void consumerMessage(String messageBody){
//        System.err.println("MessageDelegate.consumerMessage" + new String(messageBody));
//    }


}
