package com.kavito.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

/**
 * @Description: LocalDateTime
 * https://www.cnblogs.com/QQ80565970/p/10280708.html
 * @Author: kavito
 * @Date: 2019/12/20 11:03 上午
 */
public class LocalDateTimeDemo {

    public static void main(String[] args) {

        /**
         * 1、从系统时钟获取LocalDateTime实例
         */
        LocalDateTime now = LocalDateTime.now();
        // 2019-12-20T13:38:53.042
        System.out.println(now);

        /**
         * 2、解析使用"of"或者"parse"方法
         */
        LocalDateTime of = LocalDateTime.of(2019, Month.NOVEMBER, 20, 13, 40,23);
        // 2019-11-20T13:40:23
        System.out.println(of);

        LocalDateTime parse = LocalDateTime.parse("2019/12/20 14:20:23", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        LocalDateTime parse1 = LocalDateTime.parse("2019-12-20T14:20:23");
        // 2019-12-20T14:20:23
        System.out.println(parse);
        System.out.println(parse1);

        /**
         * 3、特定时间单位的时间运算
         */
        LocalDateTime nextDay = now.plusDays(1);
        LocalDateTime twoHoursBefore = now.minusHours(2);
        //now:2019-12-20T13:53:59.486
        // 下一天：2019-12-21T14:02:09.992
        System.out.println("下一天："+nextDay);
        // 两小时前：2019-12-20T12:02:09.992
        System.out.println("两小时前："+twoHoursBefore);
        //月份：DECEMBER
        System.out.println("月份："+now.getMonth());
        // 月份：12
        System.out.println("月份："+now.getMonthValue());

        /**
         * 4、获取LocalDaate ,LocalTime
         */
        LocalDate localDate = now.toLocalDate();
        LocalTime localTime = now.toLocalTime();
        // 2019-12-20
        System.out.println(localDate);
        // 14:05:15.464
        System.out.println(localTime);

        /********************** ZonedDateTime **************************/
        /**
         * 5、时区ZonedDateTime相关 API
         * https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html
         */
        // 获取"亚洲/上海"时区：
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        // Asia/Shanghai
        System.out.println(zoneId);
        // 获取所有时区
        Set<String> allZoneids = ZoneId.getAvailableZoneIds();
        System.out.println(allZoneids.toString());

        // 默认的时区
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // Asia/Shanghai
        System.out.println(defaultZoneId);

        // 转化为特定的时区中的时间
        ZonedDateTime zonedDateTime = ZonedDateTime.of(now, zoneId);
        // 2019-12-20T14:18:08.780+08:00[Asia/Shanghai]
        System.out.println(zonedDateTime);

        /**
         * 6、使用Period和Duration类
         * Period:用于计算两个日期（年月日）间隔
         * Duration:用于计算两个时间（时分秒纳秒）间隔
         */
        /********************** Period **************************/
        LocalDate nowDate = LocalDate.parse("2019-12-20");
        LocalDate plusDate = nowDate.plus(Period.ofDays(18));
        // 2020-01-07
        System.out.println("18天后的日期："+plusDate);
        // 求两个时间相隔多少天
        int days = Period.between(nowDate, plusDate).getDays();
        System.out.println("相隔："+days+"天");

        long between = ChronoUnit.DAYS.between(nowDate, plusDate);
        System.out.println("相隔："+between+"天");

        /********************** Duration **************************/
        LocalTime startLocalTime = LocalTime.of(15, 30, 0);
        LocalTime endLocalTime = startLocalTime.plus(Duration.ofSeconds(30));
        // 15:30:30
        System.out.println(endLocalTime);

        // 求时间相隔,秒，纳秒,也可以用ChronoUnit获取
        long seconds = Duration.between(startLocalTime, endLocalTime).getSeconds();
        //long seconds =ChronoUnit.SECONDS.between(startLocalTime, endLocalTime);
        // 30
        System.out.println(seconds);


        /********************** 日期和时间格式化 **************************/
        String formatLocalDateTime = now.format(DateTimeFormatter.ISO_DATE);
        // 格式化前:2019-12-20T15:00:01.527，格式化后：2019-12-20
        System.out.println("格式化前:"+now+"，格式化后："+formatLocalDateTime);

    }

}
