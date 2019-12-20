package com.kavito.time;
import	java.time.temporal.ChronoUnit;

import java.time.LocalTime;

/**
 * @Description: LocalTime
 * @Author: kavito
 * @Date: 2019/12/20 11:17 上午
 */
public class LocalTimeDemo {

    public static void main(String[] args) {
        /**
         * 1、获取系统时钟LocalTime实例
         */
        LocalTime now = LocalTime.now();
        // 11:23:16.804
        System.out.println(now);

        /**
         * 2、获取11：30 AM的LocalTime
         * of方法最多可接收四个参数：hour,minute,second,nanoOfSecond
         */
        LocalTime elevenThirty = LocalTime.parse("11:30");
        LocalTime of = LocalTime.of(11, 30);
        // 11:30
        System.out.println(elevenThirty);

        /**
         * 3、解释字符串创建LocalTime,并为其添加一个小时
         */
        LocalTime twelveThirty = LocalTime.parse("11:30").plusHours(1);
        LocalTime twelveThirty2 = elevenThirty.plus(1, ChronoUnit.HOURS);
        // 12:30
        System.out.println(twelveThirty2);

        /**
         * 4、获取一天中最大，最小，正午和午夜时间，通过常量获取
         */
        LocalTime max = LocalTime.MAX;
        LocalTime min = LocalTime.MIN;
        LocalTime noon = LocalTime.NOON;
        LocalTime midnight = LocalTime.MIDNIGHT;
        // 最大时间：23:59:59.999999999，最小时间：00:00,正午时间：12:00，午夜时间：00:00
        System.out.println("最大时间："+max+"，最小时间："+min+",正午时间："+noon+"，午夜时间："+midnight);

        /**
         * 5、将时间提取为秒，从0到24 * 60 * 60 - 1。
         */
        int second = now.toSecondOfDay();
        long nano = now.toNanoOfDay();
        System.out.println(second);
        System.out.println(nano);


    }
}
