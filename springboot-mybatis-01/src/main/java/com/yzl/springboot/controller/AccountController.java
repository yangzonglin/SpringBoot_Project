package com.yzl.springboot.controller;

import com.yzl.springboot.bean.Account;
import com.yzl.springboot.bean.Statuses;
import com.yzl.springboot.mapper.AccountMapper;
import com.yzl.springboot.mapper.StatusesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private StatusesMapper statusesMapper;

    @RequestMapping("/query")
    @ResponseBody
    public Account queryById(){
        return accountMapper.queryById("1");
    }

    @GetMapping("/queryStatuses/{id}")
    public Statuses queryStatuses(@PathVariable("id") Integer id){
        return statusesMapper.queryById(id);
    }
}
