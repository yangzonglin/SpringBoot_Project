package com.yzl.springbootrestfulcrud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author yzl
 * @Create 2019/12/13
 */
//@EnableWebMvc
@Configuration
public class MySecondConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发起hello请求，来到success页面
        registry.addViewController("/hello").setViewName("index");
    }
}
