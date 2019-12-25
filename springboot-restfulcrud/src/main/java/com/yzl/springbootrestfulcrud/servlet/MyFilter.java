package com.yzl.springbootrestfulcrud.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author yzl
 * @Create 2019/12/23
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截器生效了.....");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
