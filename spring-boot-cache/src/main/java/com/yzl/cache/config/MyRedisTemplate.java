package com.yzl.cache.config;

import com.yzl.cache.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @Author yzl
 * @Create 2020/1/9
 */
@Configuration
public class MyRedisTemplate {

    /**
     * 复制RedisAutoConfiguration中的RedisTemplate方法，set一个自定义的序列化器，并且使用该RedisTemplate进行操作
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<Object, Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(User.class);
        template.setDefaultSerializer(serializer);
        return template;
    }
}
