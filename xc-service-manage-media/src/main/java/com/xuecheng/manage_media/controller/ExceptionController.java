package com.xuecheng.manage_media.controller;

import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kavito
 * @date 2019/8/7 23:39
 */
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/test/{id}")
    public ResponseResult test(@PathVariable("id") int id) {
        if (id != 1) {
            //id不是1抛出非法参数异常
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
