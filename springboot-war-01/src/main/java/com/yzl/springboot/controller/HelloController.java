package com.yzl.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Author yzl
 * @Create 2019/12/25
 */
@Controller
public class HelloController {

    @RequestMapping("/success")
    public String success(Map map){
        map.put("ceshi","测试123");
        return "success";
    }
}
