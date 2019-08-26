package com.xuecheng.test.rabbitmq.work_queqes;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xuecheng.test.rabbitmq.utils.ConnectionUtil;

import java.util.concurrent.TimeUnit;

public class Send {
    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 循环发布任务
        for (int i = 0; i < 50; i++) {
            // 消息内容
            String message = "task .. " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            try { TimeUnit.MILLISECONDS.sleep(i*2); } catch (Exception e) { e.printStackTrace(); }
        }
        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}