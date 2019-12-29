package com.yzl.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.yzl.*.mapper")
@SpringBootApplication
public class SpringbootMybatis01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatis01Application.class, args);
    }

}
