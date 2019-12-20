package com.kavito.java8.comparator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2019/11/18 4:02 下午
 */
@Data
@AllArgsConstructor
public class Developer {

    private String name;

    private BigDecimal salary;

    private int age;
}