package com.xuecheng.manage_cms.service;


import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.manage_cms.dao.SysDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author kavito
 * @date 2019/6/17 20:54
 */
@Service
public class SysDictionarySevrice {
    @Autowired
    private SysDictionaryRepository sysDictionaryRepository;

    public SysDictionary getByType(String type){
        SysDictionary sysDictionary= sysDictionaryRepository.findByDType(type);
        return sysDictionary;
    }

}
