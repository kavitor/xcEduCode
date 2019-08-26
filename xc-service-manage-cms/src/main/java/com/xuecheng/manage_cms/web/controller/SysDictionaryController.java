package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.SysDicthinaryControllerApi;
import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.manage_cms.service.SysDictionarySevrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kavito
 * @date 2019/6/17 20:45
 */
@RestController
@RequestMapping("/sys")
public class SysDictionaryController implements SysDicthinaryControllerApi {

    @Autowired
    private SysDictionarySevrice sysDictionarySevrice;

    //根据类型查询数据字典
    @Override
    @GetMapping("/dictionary/get/{dType}")
    public SysDictionary getByType(@PathVariable("dType") String type) {
        return sysDictionarySevrice.getByType(type);
    }
}
