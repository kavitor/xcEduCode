package com.xuecheng.test.rabbitmq.springboot_amqp;

import com.xuecheng.test.rabbitmq.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Send {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    public void sendMsgByTopics(){

        /**
         * 参数：
         * 1、交换机名称
         * 2、routingKey
         * 3、消息内容
         */
        for (int i=0;i<5;i++){
            String message = "恭喜您，注册成功！userid="+i;
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME,"topic.sms.email",message);
            System.out.println(" [x] Sent '" + message + "'");
        }

    }

    //使用rabbitTemplate发送消息
  /*  @Test
    public void testSendPostPage(){

        Map message = new HashMap<>();
        message.put("pageId","5a795ac7dd573c04508f3a56");
        //将消息对象转成json串
        String messageString = JSON.toJSONString(message);
        //路由key，就是站点ID
        String routingKey = "5a751fab6abb5044e0d19ea1";
        *//**
         * 参数：
         * 1、交换机名称
         * 2、routingKey
         * 3、消息内容
         *//*
        rabbitTemplate.convertAndSend("ex_routing_cms_postpage",routingKey,messageString);

    }*/
}