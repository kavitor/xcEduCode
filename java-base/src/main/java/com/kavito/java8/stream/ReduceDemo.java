package com.kavito.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Description:Java8的Reduce语法：https://blog.csdn.net/icarusliu/article/details/79504602
 * @Author: kavito
 * @Date: 2019/12/3 11:48 上午
 */
public class ReduceDemo {



    @Test
    public void test() {
        Stream<String> s2 = Stream.of("aa", "ab", "c", "ad");
        Predicate<String> predicate2 = t -> t.contains("a");
        s2.parallel().reduce(Collections.synchronizedList(new ArrayList<>(16)), (r, t) -> {
            if (predicate2.test(t)) {
                r.add(t);
            }

            return r;
        },
                (r1, r2) -> {
                    //System.out.println(r1.size());
                    return r1;
                }).forEach(System.out::println);
    }



    @Test
    public void testReduceFilter() {
        /**
         * 模拟Filter查找其中含有字母a的所有元素，打印结果将是aa ab ad
         */
        Stream<String> s1 = Stream.of("aa", "ab", "c", "ad");

        Predicate<String> predicate = t -> t.contains("a");

        // 1、lambda语法：
        s1.reduce(new ArrayList<String>(), (r, t) -> {if (predicate.test(t)) {r.add(t);}  return r; },
                (r1, r2) -> r1).stream().forEach(System.out::println);
        // 2、非lambda语法
       /* s1.reduce(new ArrayList<String>(), new BiFunction<ArrayList<String>, String, ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ArrayList<String> strings, String s) {
                        if (predicate.test(s)){
                            strings.add(s);
                        }
                        return strings;
                    }
                },
                new BinaryOperator<ArrayList<String>>() {
                    @Override
                    public ArrayList<String> apply(ArrayList<String> strings, ArrayList<String> strings2) {
                        return strings;
                    }
                }).stream().forEach(System.out::println);*/


    }
}
