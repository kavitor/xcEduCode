package com.kavito.pattern.strategy;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2020/3/3 12:14 下午
 */
public class Test {
    public static void main(String[] args) {
        String result = getReward("GOL2D");
        System.out.println(result);

    }

    private static String getReward(String level) {

        Strategy strategy = StrategyFactory.getInstance().get(level);

        if (strategy == null){
            throw new IllegalArgumentException("对不起，您输入的段位有误！");
        }
        return strategy.reward();
    }

}

