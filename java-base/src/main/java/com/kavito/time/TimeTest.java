package com.kavito.time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2019/12/20 3:22 下午
 */
public class TimeTest {

    /**
     * 获取当前时间与当天最后一秒的时间间隔
     * @param currentDate
     * @return
     */
    public static Long getRemainSecondsOnDay(Date currentDate){
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault())
                .plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        System.out.println("midnight:"+midnight);
        LocalDateTime dateTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
        System.out.println("dateTime:"+dateTime);
        Long seconds = ChronoUnit.SECONDS.between(dateTime,midnight);
        return seconds;
    }


    // 获取当前时间与当天最后一秒的时间间隔：前天的0点-当前时间
    @Test
    public void test(){
        Long remainSecondsOnDay = TimeTest.getRemainSecondsOnDay(new Date());
        System.out.println(remainSecondsOnDay);
    }

    // 获取当前时间与当天最后一秒的时间间隔：一天的总秒数-当前时间转化的秒数
    @Test
    public void test1(){
        long max = 24*60*60;
        LocalDateTime now = LocalDateTime.now();
        System.out.println(max-now.toLocalTime().toSecondOfDay());
    }

    // 比较器值，如果更小则为负，如果更大则为正。
    @Test
    public void test3() {
        LocalDateTime date = LocalDateTime.parse("2017-02-03T12:30:30");
        System.out.println(date);
        LocalDateTime date1 = LocalDateTime.parse("2017-03-02T12:20:30");
        System.out.println(date1);
        // 1
        System.out.println(date1.compareTo(date));
        // true
        System.out.println(LocalDateTime.parse("2017-02-03T12:30:30").isBefore(LocalDateTime.parse("2017-03-02T12:20:30")));

    }
}
