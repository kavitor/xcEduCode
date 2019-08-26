package com.xuecheng.framework.domain.learning.respones;

import com.xuecheng.framework.model.response.ResultCode;
import lombok.ToString;

/**
 * @author kavito
 * @date 2019/6/30 13:54
 */
@ToString
public enum LearningCode implements ResultCode {
    LEARNING_GETMEDIA_ERROR(false,23001,"获取学习地址失败！"),
    CHOOSECOURSE_USERISNULl(false,23002,"该用户没有选课信息"),
    CHOOSECOURSE_TASKISNULL(false,23002,"该任务不存在");


    //操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private LearningCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
