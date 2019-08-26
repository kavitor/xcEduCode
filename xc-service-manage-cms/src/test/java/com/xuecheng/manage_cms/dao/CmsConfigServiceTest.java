package com.xuecheng.manage_cms.dao;

import com.xuecheng.manage_cms.service.CmsConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:11
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsConfigServiceTest {

    @Autowired
    CmsConfigService cmsConfigService;

    @Test
    public void testGetPageHtml(){
       // String pageHtml = cmsConfigService.getPageHtml("5b9b5c2fb6eb080aa0b28e56");
        String pageHtml = cmsConfigService.getPageHtml("5a795ac7dd573c04508f3a56");
        System.out.println(pageHtml);

    }

}
