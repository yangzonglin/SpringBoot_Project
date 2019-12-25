package com.yzl.springbootrestfulcrud.component;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author yzl
 * @Create 2019/12/17
 */
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getParameter("language");
        String country = request.getParameter("country");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(language) && !StringUtils.isEmpty(country)){
            locale = new Locale(language,country);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Locale locale) {

    }
}
