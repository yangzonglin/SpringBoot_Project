package com.yzl.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

@Configuration
public class CacheConfig {

    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            /**
             *
             * @param o 目标对象
             * @param method 方法
             * @param objects 参数
             * @return
             */
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return method.getName()+ Arrays.asList(objects).toString();
            }
        };
    }
}
