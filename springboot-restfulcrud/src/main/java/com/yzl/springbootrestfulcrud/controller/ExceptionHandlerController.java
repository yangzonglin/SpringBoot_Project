package com.yzl.springbootrestfulcrud.controller;

import com.yzl.springbootrestfulcrud.exception.IndexOverException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yzl
 * @Create 2019/12/20
 */
@ControllerAdvice
public class ExceptionHandlerController {

    /*//浏览器和客户端都返回的json数据
    @ExceptionHandler(IndexOverException.class)//监听哪种异常
    public String exceptionHandler(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("message",e.getMessage());
        map.put("code","500");
        return "forward:/error";
    }*/

    @ExceptionHandler(IndexOverException.class)//监听哪种异常
    public String exceptionHandler(Exception e, HttpServletRequest request){
        /**
         * Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code",400);
        Map<String,Object> map = new HashMap<>();
        map.put("message",e.getMessage());
        map.put("code","500");
        map.put("author","yzl");
        request.setAttribute("ext",map);
        return "forward:/error";
    }
}
