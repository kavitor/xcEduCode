package com.xuecheng.order.mq;

import com.xuecheng.framework.domain.task.XcTask;
import com.xuecheng.order.config.RabbitMQConfig;
import com.xuecheng.order.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author kavito
 * @date 2019/7/7 20:54
 * 任务调度：
 * 在Spring boot启动类上添加注解：@EnableScheduling
 * 秒（0~59）- 分钟（0~59）- 小时（0~23）- 月中的天（1~31）-月（1~12）- 周中的天填写MON，TUE，WED，THU，FRI，SAT,SUN，或数字1~7 1表示MON，依次类推）
 * 特殊字符介绍：
 * “/”字符表示指定数值的增量
 * “*”字符表示所有可能的值
 * “-”字符表示区间范围
 * "," 字符表示列举
 * “？”字符仅被用于月中的天和周中的天两个子表达式，表示不指定值
 * 例子：
 * 0/3 * * * * * 每隔3秒执行
 * 0 0/5 * * * * 每隔5分钟执行
 * 0 0 0 * * * 表示每天0点执行
 * 0 0 12 ? * WEN 每周三12点执行
 * 0 15 10 ? * MON-FRI 每月的周一到周五10点 15分执行
 * 0 15 10 ? * MON,FRI 每月的周一和周五10点 15分执行
 */
@Component
public class ChooseCourseTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChooseCourseTask.class);

    @Autowired
    TaskService taskService;


    /*
     * 监听学习中心向订单服务发送“完成选课的消息”
     */
    @RabbitListener(queues = RabbitMQConfig.XC_LEARNING_FINISHADDCHOOSECOURSE)
    public void receiveFinishChoosecourseTask(XcTask xcTask) {
        if (xcTask != null && StringUtils.isNotEmpty(xcTask.getId())) {
            //完成任务，向任务历史表中插入数据，同时删除任务表中相关的数据
            taskService.finishTask(xcTask.getId());
        }
    }

    @Scheduled(cron = "0/3 * * * * *")//方便测试每三秒发送一次
    //@Scheduled(cron = "* 0/1 * * * *")//上线用每1分钟
    //定时发送加选课任务
    public void sendChoosecourseTask() {
        //得到1分钟之前的时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(GregorianCalendar.MINUTE, -1);
        Date time = calendar.getTime();
        //1分钟前的任务，100条
        List<XcTask> xcTaskList = taskService.findXcTaskList(time, 100);
        //调用service发布消息，将添加选课的任务发送给mq
        for (XcTask xcTask : xcTaskList) {
            //取任务
            if (taskService.getTask(xcTask.getId(), xcTask.getVersion()) > 0) {
                String ex = xcTask.getMqExchange();//要发送的交换机
                String routingKey = xcTask.getMqRoutingkey();//发送消息要带routingKey
                taskService.publish(xcTask, ex, routingKey);
            }

        }
    }

    //----------------------------测试代码-放开@Scheduled---------------------------------------//
/*    //定义任务调试策略
     @Scheduled(cron="0/3 * * * * *")//每隔3秒去执行
    // @Scheduled(fixedRate = 3000) //在任务开始后3秒执行下一次调度
    // @Scheduled(fixedDelay = 3000) //在任务结束后3秒后才开始执行
    public void task1() {
        LOGGER.info("===============测试定时任务1开始===============");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("===============测试定时任务1结束===============");

    }

    //定义任务调试策略
    @Scheduled(cron="0/3 * * * * *")//每隔3秒去执行
    //@Scheduled(fixedRate = 3000) //在任务开始后3秒执行下一次调度
    //@Scheduled(fixedDelay = 3000) //在任务结束后3秒后才开始执行
    public void task2() {
        LOGGER.info("===============测试定时任务2开始===============");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("===============测试定时任务2结束===============");

    }*/
}
