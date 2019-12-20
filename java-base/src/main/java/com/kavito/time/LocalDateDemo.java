package com.kavito.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @Description: LocalDate
 * @Author: kavito
 * @Date: 2019/12/20 10:10 上午
 */
public class LocalDateDemo {

    public static void main(String[] args) {
        /**
         *  1、获取当前系统时钟下的日期。
         */
        LocalDate now = LocalDate.now();
        // 2019-12-20
        System.out.println(now);

        /**
         *  2、日期字符串转LocalDate对象。
         *  注意parse方法中给定的格式必须是yyyy-MM-dd，否则需要指定格式
         *  例如：2019-1-11都不行，必须严格按照2019-01-11
         *  LocalDate.parse("20190211", DateTimeFormatter.ofPattern("yyyyMMdd")
         */
        LocalDate date = LocalDate.of(2019, 02, 20);
        LocalDate date2 = LocalDate.parse("2019-02-20");
        // 2019-02-20
        System.out.println(date);

        /**
         *  3、获取当前本地日期并添加一天。
         */
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        // 2019-12-21
        System.out.println(tomorrow);

        /**
         * 4、获取当前日期并减去一个月。可以是负数
         */
        LocalDate previousMonthSameDate = now.minusMonths(1);
        LocalDate previousMonthSameDate2 = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        // 上个月日期：2019-11-20
        System.out.println("上个月的日期："+previousMonthSameDate);

        // 下一年的日期：2020-12-20
        System.out.println("下一年的日期："+now.plusYears(1));

        /**
         * 5、分析日期：2019-12-20，分别获取星期几，月中某天，年中某天。
         */
        DayOfWeek dayOfWeek = LocalDate.parse("2019-12-20").getDayOfWeek();
        int dayOfMonth = LocalDate.parse("2019-12-20").getDayOfMonth();
        int dayOfYear = LocalDate.parse("2019-12-20").getDayOfYear();
        // 星期：FRIDAY，月中天：20，年中天：354
        System.out.println("星期："+dayOfWeek+"，月中天："+dayOfMonth+"，年中天："+dayOfYear);

        /**
         * 6、判断某个日期是否闰年。
         */
        // 平年
        System.out.println(now.isLeapYear()?"闰年":"平年");

        /**
         * 7、判断日期的先后
         */
        boolean isBefore = now.isBefore(LocalDate.of(2019, 11, 20));
        boolean isAfter = now.isAfter(LocalDate.parse("2020-01-01"));
        // 今天是在2019-11-20之前吗？false，在2020-01-01之后？false
        System.out.println("今天是在2019-11-20之前吗？"+isBefore+"，在2020-01-01之后？"+isAfter);

        /**
         * 9、获取日期的边界：某天的开始和月初
         */
        LocalDateTime beginningOfDay = LocalDate.parse("20190211", DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay();
        LocalDate firstDayMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        // 一天的开始时间：2019-02-11T00:00，月初日期：2019-11-01
        System.out.println("一天的开始时间："+beginningOfDay+"，月初日期："+firstDayMonth);

        /**
         * 10、获取给定日期中的第几天日期，第几月
         */
        // 当月的第3天日期：2019-12-03，当年第90天的日期2019-03-31，当年第6月的日期：2019-06-20
        System.out.println("当月的第3天日期："+now.withDayOfMonth(3)+"，当年第90天的日期"+now.withDayOfYear(90)+"，当年第6月的日期："+now.withMonth(6));
    }




}
