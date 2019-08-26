package com.xuecheng.manage_cms;

import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.manage_cms.service.SysDictionarySevrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kavito
 * @date 2019/6/17 21:26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysDictionaryTest {

    @Autowired
    private SysDictionarySevrice sysDictionarySevrice;

    @Test
    public void test(){
        String type="200";
        SysDictionary sysDictionary=sysDictionarySevrice.getByType(type);
        System.out.println(sysDictionary);
    }

}
