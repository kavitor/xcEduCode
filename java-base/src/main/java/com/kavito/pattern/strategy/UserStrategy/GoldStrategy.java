package com.kavito.pattern.strategy.UserStrategy;

import com.kavito.pattern.strategy.Strategy;
import com.kavito.pattern.strategy.UserLevelEnum;

public class GoldStrategy implements Strategy {
    @Override
    public String reward() {
        return "英勇黄金选手，奖励【三轮摩托车】一辆";
    }

    @Override
    public String getLevel() {
        return UserLevelEnum.GOLD.getCode();
    }
}
