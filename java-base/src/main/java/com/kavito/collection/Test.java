package com.kavito.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2019/11/12 3:48 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class User{
    private String name;
}
public class Test {




    @org.junit.Test
    public void Test5() {
        List<String> listA = new ArrayList<String>();

        listA.add("流鼻涕");
        listA.add("发烧");
        listA.add("严重感冒");
        listA.add("头疼");
        listA.add("发烧");
        String str1 = listA.stream().distinct().collect(Collectors.joining(";"));
        String str2 = listA.stream().distinct().collect(Collectors.joining(";","[","]"));

        System.out.println(str1);
    }


    @org.junit.Test
    public void Test4() {
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();

        s1.add("a");
        s1.add("b");
        s1.add("c");

        s2.addAll(s1);
        System.out.println("s1"+s1.toString());
        System.out.println("s2"+s2.toString());
    }

    @org.junit.Test
    public void Test3() {
        List<String> listA = new ArrayList<String>();
        List<String> listB = new ArrayList<String>();

        listA.add("a");
        listA.add("b");
        listA.add("c");
        listA.add("d");

        listA.retainAll(listB);
        System.out.println(listA.size()>0);

    }


    @org.junit.Test
    public void Test() {
        List<String> listA = new ArrayList<String>();
        List<String> listB = new ArrayList<String>();

        listA.add("a");
        listA.add("b");
        listA.add("c");
        listA.add("d");
        listB.add("a");
        listB.add("A");
        listB.add("b");

        if(listB.stream().anyMatch(y->Objects.equals("",y))){
            System.out.println("空串");
        }else{
            System.out.println("没有空串");
        }

    }

    @org.junit.Test
    public void Test2() {
        List<User> listA = new ArrayList<>();
        List<User> listB = new ArrayList<>();

        listA.add(new User("zhangsan"));
        listA.add(new User("lisi"));
        listA.add(new User("32"));
        listA.add(new User("45"));
        listA.add(new User("34"));
        listA.add(new User("415"));
        listA.add(new User("fg"));
        listA.add(new User("wer"));

        /*listB.add(new User("zhangsan"));
        listB.add(new User("zhangsan"));
        listB.add(new User(""));
*/
       /*if(listB.stream().anyMatch(y -> Objects.equals("zhangsan" , y.getName()))){
           System.out.println("haha");
       }else{
           System.out.println("dfjd");
       }*/
        System.out.println(listA.toString());
        Collections.shuffle(listA);
        System.out.println(listA.toString());
        List<User> list = listA.stream().limit(100).collect(Collectors.toList());
        System.out.println(list.toString());
    }

    @org.junit.Test
    public void testi(){

        System.out.println((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))));

    }
}
