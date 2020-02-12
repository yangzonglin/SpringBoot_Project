package com.yzl.eurekacustomer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/ticket")
    public String getTicket(){
        //通过url调用，项目中可使用feign调用
        //http:// + 注册到eureka中的名字 + 服务请求
        String s = restTemplate.getForObject("http://SPRINGCLOUD-EUREKA-PROVIDER/getTicket",String.class);
        return s;
    }
}
