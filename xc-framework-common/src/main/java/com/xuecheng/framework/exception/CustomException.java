package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

 /**
 * 自定义异常类型
 * @author kavito
 * @date 2019/5/31 16:39
 */
public class CustomException extends RuntimeException {

    //错误代码
    ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        this.resultCode = resultCode;
    }
    public ResultCode getResultCode(){
        return resultCode;
    }

}
