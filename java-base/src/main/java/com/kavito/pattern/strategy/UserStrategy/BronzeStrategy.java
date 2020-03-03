package com.kavito.pattern.strategy.UserStrategy;

import com.kavito.pattern.strategy.Strategy;
import com.kavito.pattern.strategy.UserLevelEnum;

public class BronzeStrategy implements Strategy {

    @Override
    public String reward() {
        return "热血青铜选手，奖励【单车】一辆";
    }

    @Override
    public String getLevel() {
        return UserLevelEnum.BRONZE.getCode();
    }
}