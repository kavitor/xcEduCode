package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.manage_course.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kavito
 * @date 2019/6/16 16:32
 */
@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    //查询课程计划
    public CategoryNode list(){
        CategoryNode categoryNode = categoryMapper.list();
        return categoryNode;
    }

}
