package com.yzl.springbootrestfulcrud.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yzl
 * @Create 2019/12/18
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!StringUtils.isEmpty(request.getSession().getAttribute("login"))){
            return true;
        }
        request.setAttribute("msg","请先登录再试");
        request.getRequestDispatcher("/index.html").forward(request,response);
        return false;
    }
}
