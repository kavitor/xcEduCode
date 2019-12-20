package com.kavito.Collection;

import java.util.*;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2019/11/9 5:20 下午
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> listA = new ArrayList<String>();
        List<String> listB = new ArrayList<String>();
        Set<String>  setC = new HashSet<>();

        listA.add("A");
        listA.add("B");
        listA.add("B");
        listA.add("C");
        listB.add("B");
        listB.add("C");
        setC.add("F");
        setC.add("E");
        setC.add("G");

        List<String> newList = new ArrayList<>(Arrays.asList(new String[listA.size()]));
        Collections.copy(newList,listA);
        boolean b = newList.retainAll(setC);
        if(newList.size()>0){
            System.out.println(b+"mei有交集"+newList.toString());
        }

    }
}
