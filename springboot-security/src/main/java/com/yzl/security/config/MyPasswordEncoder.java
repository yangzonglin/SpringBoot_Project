package com.yzl.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义密码编辑器
 * Spring Security 升级到5版本后密码支持多种加密格式
 * 启用Spring Security登录必须要配置
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
