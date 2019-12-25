package com.yzl.springbootrestfulcrud.controller;

import com.yzl.springbootrestfulcrud.exception.IndexOverException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author yzl
 * @Create 2019/12/11
 */
@Controller
public class HelloController {

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        //classpath:/templates/success.html
        map.put("hello","<h1>hello</h1>");
        map.put("names", Arrays.asList("zhangsan","wangwu","liusi"));
        return "success";
    }

    @RequestMapping("/excep")
    public String excep(){
        if(1 == 1) {
            throw new IndexOverException();
        }
        return "success";
    }
}
