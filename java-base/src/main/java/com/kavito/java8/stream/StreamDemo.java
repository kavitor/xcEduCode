package com.kavito.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    String name;
    Integer age;
}

public class StreamDemo {

    public  Person getPerson() {
        Person person = new Person();
        person.setName(UUID.randomUUID().toString().replaceAll("-", ""));
        person.setAge((int) (Math.random() * 20));
        return person;
    }


    // list:filter map forEach
    @Test
    public void test1() {
        //取出年龄大于13的Person的名字：
        List<Person> list = new ArrayList<Person>();
        for (int i = 1; i <= 10; i++) {
            list.add(getPerson());
        }
        List<String> nameList = list.stream().filter(x -> x.getAge() > 13).map(Person::getName).collect(Collectors.toList());
        //nameList.forEach(y-> System.out.println(y));
        nameList.forEach(System.out::println);

    }

    // list<Map>:
   /* @Test
    public void test2() {
       List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
        for (int i = 1; i <= 10; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put(String.valueOf(i),getPerson());
            listMap.add(map);
        }
        listMap.stream().map((k,v)->);

    }*/



}

