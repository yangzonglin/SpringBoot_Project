package com.yzl.eurekacustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient  //开启服务发现的功能
@SpringBootApplication
public class SpringcloudEurekaCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaCustomerApplication.class, args);
    }

    @LoadBalanced   //（发送http请求）启用负载均衡机制
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
