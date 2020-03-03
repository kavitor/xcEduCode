package com.kavito.pattern.strategy;

import com.kavito.designPattern.strategy.UserStrategy.*;
import com.kavito.pattern.strategy.UserStrategy.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StrategyFactory {

    private Map<String, Strategy> map;

    public StrategyFactory() {

        List<Strategy> strategies = new ArrayList<>();

        strategies.add(new BronzeStrategy());
        strategies.add(new SilverStrategy());
        strategies.add(new GoldStrategy());
        strategies.add(new PlatinumStrategy());
        strategies.add(new DiamondStrategy());
        strategies.add(new CrownStrategy());
        strategies.add(new TrumpStrategy());
        strategies.add(new GodStrategy());

        // 添加到map中
        map = strategies.stream().collect(Collectors.toMap(Strategy::getLevel, strategy -> strategy));


    }
    // 静态内部类获取工程实例
    public static class Holder {
        public static StrategyFactory instance = new StrategyFactory();
    }

    public static StrategyFactory getInstance() {
        return Holder.instance;
    }

    public Strategy get(String level) {
        return map.get(level);
    }
}
