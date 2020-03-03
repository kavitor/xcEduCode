package com.kavito.pattern.strategy.UserStrategy;

import com.kavito.pattern.strategy.Strategy;
import com.kavito.pattern.strategy.UserLevelEnum;

public class DiamondStrategy implements Strategy {
    @Override
    public String reward() {
        return "砖石选手，奖励【吉普车】一辆";
    }

    @Override
    public String getLevel() {
        return UserLevelEnum.DIAMOND.getCode();
    }
}
