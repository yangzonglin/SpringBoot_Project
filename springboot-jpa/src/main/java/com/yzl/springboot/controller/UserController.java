package com.yzl.springboot.controller;

import com.yzl.springboot.dao.UserRepository;
import com.yzl.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author yzl
 * @Create 2019/12/30
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/user/{id}")
    public User queryById(@PathVariable("id") Integer id){
        User user = userRepository.findById(id).get();
        return user;
    }
}
