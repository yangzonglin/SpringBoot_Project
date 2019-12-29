package com.yzl.springboot.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class MyBaitsConfig {

    @Bean
    public ConfigurationCustomizer configuration(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                //开启驼峰命名法规则
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
