package com.kavito.pattern.strategy.UserStrategy;

import com.kavito.pattern.strategy.Strategy;
import com.kavito.pattern.strategy.UserLevelEnum;

public class TrumpStrategy implements Strategy {
    @Override
    public String reward() {
        return "超级王牌选手，奖励【粉色玛萨拉蒂】一辆";
    }

    @Override
    public String getLevel() {
        return UserLevelEnum.TRUMP.getCode();
    }
}
