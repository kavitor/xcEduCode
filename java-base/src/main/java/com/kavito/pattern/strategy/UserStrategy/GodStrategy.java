package com.kavito.pattern.strategy.UserStrategy;

import com.kavito.pattern.strategy.Strategy;
import com.kavito.pattern.strategy.UserLevelEnum;

public class GodStrategy implements Strategy {
    @Override
    public String reward() {
        return "无敌战神选手，奖励【黄金玛萨拉蒂】一辆";
    }

    @Override
    public String getLevel() {
        return UserLevelEnum.GOD.getCode();
    }
}
