package com.kavito.pattern.strategy.UserStrategy;

import com.kavito.pattern.strategy.Strategy;
import com.kavito.pattern.strategy.UserLevelEnum;

public class CrownStrategy implements Strategy {
    @Override
    public String reward() {
        return "荣耀皇冠选手，奖励【AE86】一辆";
    }

    @Override
    public String getLevel() {
        return UserLevelEnum.CROWN.getCode();
    }
}
