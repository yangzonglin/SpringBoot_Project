package com.yzl.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/vip1")
    public String vip1(){
        return "level1/vip1";
    }

    @RequestMapping("/vip2")
    public String vip2(){
        return "level1/vip2";
    }

    @RequestMapping("/vip3")
    public String vip3(){
        return "level1/vip3";
    }
}
