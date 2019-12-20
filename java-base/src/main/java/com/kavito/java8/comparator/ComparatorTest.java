package com.kavito.java8.comparator;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2019/11/18 4:00 下午
 */
public class ComparatorTest {

    private static List<Developer> getDevelopers() {
        List<Developer> result = new ArrayList<Developer>();
        result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("10000"), 23));
        return result;
    }



    //java8之前的写法：比较器,年龄升序
    @Test
    public void testBefore(){

        List<Developer> listDevs = getDevelopers();

        Collections.sort(listDevs, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(listDevs.toString());
    }

    //java8之后
    @Test
    public void testAfter(){

        List<Developer> listDevs = getDevelopers();

        System.out.println("-------⬇⬇年龄升序⬇⬇-------");
        listDevs.sort((Developer o1,Developer o2)->o1.getAge()-o2.getAge());
        System.out.println(listDevs.toString());

        System.out.println("--------⬇⬇名字升序⬇⬇--------");

        listDevs.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        System.out.println(listDevs.toString());

        System.out.println("---------⬇⬇工资升序⬇⬇-------");

        listDevs.sort(Comparator.comparing(Developer::getSalary));
        System.out.println(listDevs.toString());

        System.out.println("---------⬇⬇工资降序⬇⬇-------");

        listDevs.sort(Comparator.comparing(Developer::getSalary).reversed());
        System.out.println(listDevs.toString());

    }
}
