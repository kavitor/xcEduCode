package com.kavito.java8;

public class Regular {

    /**
     * 名称：20位，汉字，数字，字母，-，_,()
     * @param args
     */
    public static void main(String[] args) {
        String str="A90注释(_d-)（）";
        String parrent="[ \\u4e00-\\u9fa5\\w\\-\\(\\)\\（\\）]{1,20}";
        System.out.println(str.matches(parrent));
    }
}