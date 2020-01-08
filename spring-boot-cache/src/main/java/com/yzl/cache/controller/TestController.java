package com.yzl.cache.controller;

import com.yzl.cache.mapper.UserMapper;
import com.yzl.cache.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserMapper userMapper;

    @Cacheable(cacheNames = "user",key = "#id")
    @GetMapping("/testId/{id}")
    public User getById(@PathVariable("id") Integer id){
        return userMapper.getById(id);
    }
}
