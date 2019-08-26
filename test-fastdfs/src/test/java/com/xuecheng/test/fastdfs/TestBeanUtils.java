package com.xuecheng.test.fastdfs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kavito
 * @date 2019/6/24 14:58
 */
public class TestBeanUtils {


    @Data
    @AllArgsConstructor
    @ToString
    @NoArgsConstructor
    class Person{
        private String name;
        private Integer age;
    }

    @Data
    @AllArgsConstructor
    @ToString
    @NoArgsConstructor
    class User{
        private String name;
        private Integer age;
        private String sex;
    }

    @Test
    public void test(){
        //BeanUtils.copyProperties(Object sourse,Object target),作用就是把两个对象中相同字段进行赋值。将sourse拷贝到target
        // 当然p2不一定也是Person对象，其他对象也可以。只要两个对象中有相同的成员变量就可以赋值。
        Person p1 = new Person("刘备", 30);
        Person p2 = new Person("关羽", 25);
        Person p3 = new Person("张飞", 20);
        User user=new User();
        System.out.println("赋值前："+p2);
        BeanUtils.copyProperties(p1, p2);//将p1的值拷贝到p2去
        System.out.println("赋值后："+p2);
        BeanUtils.copyProperties(p1, user);
        System.out.println("user："+user);

        //集合类型赋值：将list的值赋值给newlist
        List<Person> list = new ArrayList<Person>(){{
            add(p1);
            add(p2);
            add(p3);
        }};

        List<Person> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Person p = new Person();
                BeanUtils.copyProperties(list.get(i),p);
                newList.add(p);
            }
        System.out.println(newList);
    }
}
