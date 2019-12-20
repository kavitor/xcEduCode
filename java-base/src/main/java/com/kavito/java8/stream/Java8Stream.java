package com.kavito.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Emp{
    String name;
    Integer age;
    Double salary;
}

public class Java8Stream {

    /**
     * 元素一对多转换：对原Stream中的所有元素使用传入的Function进行处理，每个元素经过处理后生成一个多个元素的Stream对象，
     * 然后将返回的所有Stream对象中的所有元素组合成一个统一的Stream并返回；
     * 需求：假设要对一个String类型的Stream进行处理，将每一个元素的拆分成单个字母，并打印：
     */
    @Test
    public void testFlatMap() {
        Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa");
        s.flatMap(n -> Stream.of(n.split(""))).forEach(System.out::print);
    }



    @Test
    public void testTakeWhile(){
        Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa", "taaa");
        //以下结果将打印： "test", "t1", "t2", "teeeee"，最后的那个taaa不会进行打印,因为aaaa断了
        //s.takeWhile(n -> n.contains("t")).forEach(System.out::println);
    }






    public static List<Emp> list = new ArrayList<>();
    static {
        list.add(new Emp("张飞", 25, 1000.0));
        list.add(new Emp("关羽", 27, 2000.0));
        list.add(new Emp("刘备", 30, 3000.0));
        list.add(new Emp("吕布", 29, 4000.0));
        list.add(new Emp("黄忠", 48, 5000.0));
        list.add(new Emp("赵云", 26, 9000.0));
        list.add(new Emp("马超", 33, 10000.0));
        list.add(new Emp("诸葛亮", 32, 15000.0));
    }

    public static void println(Stream<Emp> stream) {
        stream.forEach(emp -> {
            System.out.println(String.format("名字：%s，年纪：%s，薪水：%s", emp.getName(), emp.getAge(), emp.getSalary()));
        });
    }


    public static void main(String[] args) {
        // 对数组流，先过滤重复，在排序，再控制台输出 1，2，3
        Arrays.asList(3, 1, 2, 1).stream().distinct().sorted().forEach(str -> {
            System.out.println(str);
        });
        System.out.println("======薪水排序=======");
        // 对list里的emp对象，取出薪水，并对薪水进行排序，然后输出薪水的内容，map操作，改变了Strenm的泛型对象
        list.stream().map(emp -> emp.getSalary()).sorted().forEach(salary -> {
            System.out.println(salary);
        });
        System.out.println("=====根据emp的属性name，进行排序=======");
        // 根据emp的属性name，进行排序
        println(list.stream().sorted(Comparator.comparing(Emp::getName)));

        // 给年纪大于30岁的人，薪水提升1.5倍，并输出结果
        Stream<Emp> stream = list.stream().filter(emp -> {
            return emp.getAge() > 30;
        }).peek(emp -> {
            emp.setSalary(emp.getSalary() * 1.5);
        });
        System.out.println("=====年龄超30加薪=======");
        println(stream);

        System.out.println("=====数字迭代，skip,limit=======");
        // 数字从1开始迭代（无限流），下一个数字，是上个数字+1，忽略前5个 ，并且只取10个数字
        // 原本1-无限，忽略前5个，就是1-5数字，不要，从6开始，截取10个，就是6-15
        Stream.iterate(1, x -> ++x).skip(5).limit(10).forEach(System.out::println);
    }


}
