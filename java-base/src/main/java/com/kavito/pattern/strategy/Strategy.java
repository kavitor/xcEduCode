package com.kavito.pattern.strategy;

public interface Strategy {
    /**
     * 赛季末根据段位奖励
     * @return
     */
    String reward();

    /**
     * 选手段位
     * @return
     */
    String getLevel();


}