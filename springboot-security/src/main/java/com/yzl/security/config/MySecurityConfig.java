package com.yzl.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 设置spring security配置
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    //定制请求的授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/vip1","/vip3").hasRole("VIP1").
        antMatchers("/vip2").hasRole("VIP2");

        //开启自动配置的登陆功能,不设置参数为跳转spring security默认的登陆页面（/login）
        /**
         * 1./login来到登录页
         * 2.重定向到/login？error表示登录失败
         * 3.更多详细规则
         * 4.loginPage:自定义登录页地址,配置之后跳转登录页的地址和登录页登录请求地址都为该地址（登录操作spring security自动处理）
         * 5.usernameParameter和passwordParameter：自定义登录页后，修改用户名和密码表单的name值的默认值
         */
        http.formLogin().loginPage("/loginPage").usernameParameter("user").passwordParameter("pwd");

        /**
         * 开启自动配置的注销功能
         * 1.访问/logout表示用户注销，清空session
         * 2.注销成功会返回/login?logout页面
         * 3.logoutSuccessUrl：设置注销成功之后的跳转页面，默认跳转页面为2所述
         */
        http.logout().logoutSuccessUrl("/");

        /**
         * 开启自动配置的记住我
         * 登录成功后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登陆
         * 点击注销会删除cookie（或等到浏览器的cookie到期失效）
         * 1.rememberMeParameter：自定义的登录页面的记住我，设置表单提交的name值
         */
        http.rememberMe().rememberMeParameter("remember");
    }


    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //账号密码匹配存入内存（正式项目应从数据库取值）
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).
                withUser("yzl").password("123456").roles("VIP1","VIP2").
                and().
                withUser("hyj").password("123456").roles("VIP1");
    }
}
