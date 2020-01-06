package com.yzl.cache.controller;

import com.yzl.cache.mapper.UserMapper;
import com.yzl.cache.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/user/{id}")
    public User getById(@PathVariable("id") Integer id){
        return userMapper.getById(id);
    }

    @RequestMapping("/user")
    public User updateById(User user){
        userMapper.updateById(user);
        return user;
    }
}
