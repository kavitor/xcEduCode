package com.xuecheng.framework.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 *  Feign拦截器:远程调用将header传到被调用的服务（之前header：Authorization中包含JWT令牌）
 *  1、添加spring-cloud-starter-openfeign依赖
 *  2、定义在common工程是因为很多服务要用远程调用进行服务认证
 *  3、定义在调用者服务（course服务）的启动类定义一个@bean返回FeignClientInterceptor对象：course服务调用cms服务
 *
 *  拓展：如果采用RestTemplate方式远程调用需要传递头信息，就需要采用
 *  ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
 *  在httpEntity设置body和header就行了
 * @author kavito
 * @date 2019/7/4 10:44
 */
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            //取出当前请求的header，（包含jwt令牌的header）
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                //遍历取出每个头信息，一一往下传
                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();//authorization
                    String headerValue = request.getHeader(headerName);//Bearer eyjHkleOkND...dQrOd,其中eyjHkleOkND...dQrOd就是JWT令牌
                    // 将header向下传递
                    requestTemplate.header(headerName, headerValue);

                }
            }
        }
    }
}
