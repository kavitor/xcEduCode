package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * @author kavito
 * @date 2019/6/5 23:05
 */
@Controller
public class CmsPagePreviewController extends BaseController {
    @Autowired
    CmsConfigService cmsConfigService;

    //页面预览
    @RequestMapping(value="/cms/preview/{pageId}",method = RequestMethod.GET)
    public void preview(@PathVariable("pageId") String pageId) throws IOException {
        //执行静态化
        String pageHtml = cmsConfigService.getPageHtml(pageId);
        //通过response对象将内容输出
        ServletOutputStream outputStream = response.getOutputStream();
        //由于Nginx先请求cms的课程预览功能得到html页面，再解析页面中的ssi标签，这里必须保证cms页面预览返回的
        //页面的Content-Type为text/html;charset=utf-8
        response.setHeader("Content-type","text/html;charset=utf-8");
        outputStream.write(pageHtml.getBytes("utf-8"));

    }
}
