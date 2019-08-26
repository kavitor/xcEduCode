package com.xuecheng.test.rabbitmq.receiver;

/**
 * @author kavito
 * @date 2019/6/12 12:48
 */
//第二种可以向生产者那样通过配置配配置队列以及绑定关系
/*
@Component
public class ReceiveHandler2 {
    //监听email队列
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_EMAIL})
    public void receive_email(String msg, Message message, Channel channel){
        System.out.println(msg);
    }
    //监听sms队列
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_SMS})
    public void receive_sms(String msg,Message message,Channel channel){
        System.out.println(msg);
    }
}*/
