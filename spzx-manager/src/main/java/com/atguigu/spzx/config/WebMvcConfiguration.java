package com.atguigu.spzx.config;

import com.atguigu.spzx.interceptor.LoginHandlerInterceptor;
import com.atguigu.spzx.properties.UserAuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * projectName: com.atguigu.spzx.config
 *
 * @author: ppp
 * time: 2023/7/29 9:45
 * description:
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {
    //注册拦截器
    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;
    @Autowired
    private UserAuthProperties userAuthProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor)
                .excludePathPatterns(userAuthProperties.getNoAuthUrls())
                .addPathPatterns("/**");
    }

    //解决跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").//添加路径规则
                allowCredentials(true).
                allowedOriginPatterns("*").
                allowedMethods("*").
                allowedHeaders("*");
    }
}
