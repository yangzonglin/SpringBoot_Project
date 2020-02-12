package com.yzl.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.引入依赖
 * 2.配置dubbo的注册中心配置
 * 3.引用服务
 */
@SpringBootApplication
public class DubboCustomerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboCustomerUserApplication.class, args);
    }

}
