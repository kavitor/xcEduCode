package com.kavito.pattern.strategy.UserStrategy;

import com.kavito.pattern.strategy.Strategy;
import com.kavito.pattern.strategy.UserLevelEnum;

public class SilverStrategy implements Strategy {
    @Override
    public String reward() {
        return "不屈白银选手，奖励【摩托车】一辆";
    }

    @Override
    public String getLevel() {
        return UserLevelEnum.SILVER.getCode();
    }
}
