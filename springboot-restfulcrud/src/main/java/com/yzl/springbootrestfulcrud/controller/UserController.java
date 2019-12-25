package com.yzl.springbootrestfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author yzl
 * @Create 2019/12/18
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public String login(String username, String password, HttpServletRequest request, Map map){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            request.getSession().setAttribute("login",username);
            return "redirect:/main.html";
        }
        map.put("msg","账号密码错误！");
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(String username, String password, HttpServletRequest request, Map map){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            request.getSession().setAttribute("login",username);
            return "redirect:/main.html";
        }
        map.put("msg","账号密码错误！");
        return "index";
    }
}
