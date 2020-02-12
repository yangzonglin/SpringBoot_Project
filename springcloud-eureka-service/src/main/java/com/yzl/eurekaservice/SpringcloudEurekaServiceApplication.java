package com.yzl.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 * 1.配置Eureka信息（yml）
 * 2.@EnableEurekaServer启用注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringcloudEurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaServiceApplication.class, args);
    }

}
