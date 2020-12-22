package com.zhangbo;


import com.zhangbo.common.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description:拦截器
 * @Author: zhangbo
 * @Date: 2020/12/22 16:05
 **/
@Configuration
public class WebRootConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        System.out.println("拦截中.....");
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


}