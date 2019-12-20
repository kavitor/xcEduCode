package com.kavito.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:https://blog.csdn.net/vbirdbest/article/details/80216713
 * @Author: kavito
 * @Date: 2019/11/19 10:44 上午
 */
public class Collect {

    @Test
    public void testToCollection(){
        List<Integer> list = Arrays.asList(1, 2, 3);

        // [10, 20, 30]
        List<Integer> collect = list.stream().map(i -> i * 10).collect(Collectors.toList());

        // [20, 10, 30]
        Set<Integer> collect1 = list.stream().map(i -> i * 10).collect(Collectors.toSet());

        // {key1=value:10, key2=value:20, key3=value:30}
        Map<String, String> collect2 = list.stream().map(i -> i * 10).collect(Collectors.toMap(key -> "key" + key/10, value -> "value:" + value));

        // [1, 3, 4]
        TreeSet<Integer> collect3= Stream.of(1, 3, 4).collect(Collectors.toCollection(TreeSet::new));
    }




    @Data
    @ToString
    @AllArgsConstructor
    @RequiredArgsConstructor
    public class User {
        private Long id;
        private String username;
    }

    @Test
    public void testToMap() {
        List<User> userList = Arrays.asList(
                new User(1L, "mengday"),
                new User(2L, "mengdee"),
                new User(3L, "mengdy")
        );

        // toMap 可用于将List转为Map，便于通过key快速查找到某个value
        Map<Long, User> userIdAndModelMap = userList.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        User user = userIdAndModelMap.get(1L);
        // User(id=1, username=mengday)
        System.out.println(user);

        Map<Long, String> userIdAndUsernameMap = userList.stream().collect(Collectors.toMap(User::getId, User::getUsername));
        String username = userIdAndUsernameMap.get(1L);
        // mengday
        System.out.println(username);
    }



    @Test
    public void testJoining(){
        // a,b,c
        List<String> list2 = Arrays.asList("a", "b", "c");
        String result = list2.stream().collect(Collectors.joining(","));

        // Collectors.joining(",")的结果是：a,b,c  然后再将结果 x + "d"操作, 最终返回a,b,cd
        String str= Stream.of("a", "b", "c").collect(Collectors.collectingAndThen(Collectors.joining(","), x -> x + "d"));
    }

    @Test
    public void test(){
        // 求最值 3
        List<Integer> list = Arrays.asList(1, 2, 3);
        Integer maxValue = list.stream().collect(Collectors.collectingAndThen(Collectors.maxBy((a, b) -> a - b), Optional::get));


        // 最小值 1
        Integer minValue = list.stream().collect(Collectors.collectingAndThen(Collectors.minBy((a, b) -> a - b), Optional::get));

        // 求和 6
        Integer sumValue = list.stream().collect(Collectors.summingInt(item -> item));

        // 平均值 2.0
        Double avg = list.stream().collect(Collectors.averagingDouble(x -> x));
    }


    @Test
    public void test2(){
        // 映射：先对集合中的元素进行映射，然后再对映射的结果使用Collectors操作
        // A,B,C
        Stream.of("a", "b", "c").collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.joining(",")));
    }

}
