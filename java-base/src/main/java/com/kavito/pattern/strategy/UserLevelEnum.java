package com.kavito.pattern.strategy;

public enum UserLevelEnum {

    /**
     * 热血青铜、不屈白银、英勇黄金、坚韧铂金、不朽星钻、荣耀皇冠、超级王牌、无敌战神
     */
    BRONZE("BRONZE","热血青铜"),
    SILVER("SILVER","不屈白银"),
    GOLD("GOLD","英勇黄金"),
    PLATINUM("PLATINUM","坚韧铂金"),
    DIAMOND("DIAMOND","不朽星钻"),
    CROWN("CROWN","荣耀皇冠"),
    TRUMP("TRUMP","超级王牌"),
    GOD("GOD","无敌战神");

    private String code;
    private String name;

    private UserLevelEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode(){
        return this.code;
    }

    public String getName() {
        return this.name;
    }

}
