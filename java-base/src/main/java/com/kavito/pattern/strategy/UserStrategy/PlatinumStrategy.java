package com.kavito.pattern.strategy.UserStrategy;

import com.kavito.pattern.strategy.Strategy;
import com.kavito.pattern.strategy.UserLevelEnum;

public class PlatinumStrategy implements Strategy {
    @Override
    public String reward() {
        return "坚韧铂金选手，奖励【皮卡车】一辆";
    }

    @Override
    public String getLevel() {
        return UserLevelEnum.PLATINUM.getCode();
    }
}
