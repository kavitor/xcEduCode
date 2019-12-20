package com.kavito.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2019/11/19 10:47 上午
 */
public class GroupBy {

    @Data
    @AllArgsConstructor
    public static class User {
        private Long id;
        private String username;
        private Integer type;
    }

    @Test
    public void testGroupBy(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 奇偶数分组：奇数分一组，偶数分一组
        // groupingBy(Function<? super T, ? extends K> classifier) 参数是Function类型，Function返回值可以是要分组的条件，也可以是要分组的字段
        // 返回的结果是Map，其中key的数据类型为Function体中计算类型，value是List<T>类型，为分组的结果
        Map<Boolean, List<Integer>> result = list.stream().collect(Collectors.groupingBy(item -> item % 2 == 0));
        // {false=[1, 3, 5, 7, 9], true=[2, 4, 6, 8, 10]}
        System.out.println(result);


        // partitioningBy 用于分成两组的情况
        Map<Boolean, List<Integer>> twoPartiton = list.stream().collect(Collectors.partitioningBy(item -> item % 2 == 0));
        System.out.println(twoPartiton);


        User user = new User(1L, "zhangsan", 1);
        User user2 = new User(2L, "lisi", 2);
        User user3 = new User(3L, "wangwu", 3);
        User user4 = new User(4L, "fengliu", 1);
        List<User> users = Arrays.asList(user, user2, user3, user4);
        // 根据某个字段进行分组
        Map<Integer, List<User>> userGroup = users.stream().collect(Collectors.groupingBy(item -> item.type));

        /**
         * key 为要分组的字段
         * value 分组的结果
         * {
         *  1=[User{id=1, username='zhangsan', type=1}, User{id=4, username='fengliu', type=1}],
         *  2=[User{id=2, username='lisi', type=2}],
         *  3=[User{id=3, username='wangwu', type=3}]
         * }
         */
        System.out.println(userGroup);
    }





    @Data
    @AllArgsConstructor
    public static class Foo {
        private Integer code;
        private Integer count;

    }

    // 分组并对分组中的数据统计
    @Test
    public void testGroupBy2() {
        Foo foo1 = new Foo(1, 2);
        Foo foo2 = new Foo(2, 23);
        Foo foo3 = new Foo(2, 6);
        List<Foo> list = new ArrayList<>(4);
        list.add(foo1);
        list.add(foo2);
        list.add(foo3);
        Map<Integer, IntSummaryStatistics> collect = list.stream().collect(Collectors.groupingBy(Foo::getCode, Collectors.summarizingInt(Foo::getCount)));
        IntSummaryStatistics statistics1 = collect.get(1);
        IntSummaryStatistics statistics2 = collect.get(2);
        System.out.println(statistics1.getSum());
        System.out.println(statistics1.getAverage());
        System.out.println(statistics1.getMax());
        System.out.println(statistics1.getMin());
        System.out.println(statistics1.getCount());

        System.out.println(statistics2.getSum());
        System.out.println(statistics2.getAverage());
        System.out.println(statistics2.getMax());
        System.out.println(statistics2.getMin());
        System.out.println(statistics2.getCount());
    }




    @Test
    public void testReducing(){

        // sum: 是每次累计计算的结果，b是Function的结果
        System.out.println(Stream.of(1, 3, 4).collect(Collectors.reducing(0, x -> x + 1, (sum, b) -> {
            System.out.println(sum + "-" + b);
            return sum + b;
        })));


        // 下面代码是对reducing函数功能实现的描述，用于理解reducing的功能
        int sum = 0;
        List<Integer> list3 = Arrays.asList(1, 3, 4);
        for (Integer item : list3) {
            int b = item + 1;
            System.out.println(sum + "-" + b);
            sum = sum + b;
        }
        System.out.println(sum);


        // 注意reducing可以用于更复杂的累计计算，加减乘除或者更复杂的操作
        // result = 2 * 4 * 5 = 40
        System.out.println(Stream.of(1, 3, 4).collect(Collectors.reducing(1, x -> x + 1, (result, b) -> {
            System.out.println(result + "-" + b);
            return result * b;
        })));
    }

}
