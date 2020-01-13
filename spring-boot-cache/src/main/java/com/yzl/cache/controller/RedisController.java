package com.yzl.cache.controller;

import com.yzl.cache.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存默认使用的是SimpleCacheConfiguration，其中manager和cache是ConcurrentMapCacheManager和ConcurrentMapCache，
 * 数据存在ConcurrentMap<Object,Object>中。
 * 开发过程中一般使用的是缓存中间件：redis、memcached、ehcache
 *
 * 整合redis作为缓存中间件：
 *  Redis是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以作数据库、缓存、消息中间件。
 *      1.安装redis
 *      2.引入redis的starter
 *      3.配置redis
 *      4.使用redis
 *          原理：CacheManager===Cache 缓存组件来实际给缓存中存取数据
 *          1）引入redis的starter，容器中保存的是RedisCacheManager
 *          2）RedisCacheManager 帮我们创建 RedisCache 来作为缓存组件；RedisCache通过操作Redis来缓存数据
 *          3）默认保存数据K-V都是object；利用jdk的序列化保存。若要使用json来保存，就需要自定义RedisTemplate<Object,Object>来操作Redis；
 *              再将自定义的RedisTemplate注入到自定义的RedisManager中即可
 *
 *  使用代码调用缓存，可以通过注入cacheManager，获得cache，通过cache进行CRUD操作
 *
 *
 * @Author yzl
 * @Create 2020/1/9
 */
@RestController
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;//操作k-v都是对象的

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串的

    @Autowired
    RedisTemplate myRedisTemplate;//自定义的template

    /**
     * Redis常见的五大数据类型
     * String(字符串)、List(列表)、Set(集合)、Hash(散列)、ZSet(有序集合)
     * 操作方法：
        stringRedisTemplate.opsForValue()
        stringRedisTemplate.opsForList();
        stringRedisTemplate.opsForSet();
        stringRedisTemplate.opsForHash();
        stringRedisTemplate.opsForZSet();
     具体的CRUD等在上面方法后面追加
     * @return
     */
    public String testString(){
        stringRedisTemplate.opsForValue().append("","");
        stringRedisTemplate.opsForValue().get("");
        stringRedisTemplate.opsForList().leftPush("","");
        stringRedisTemplate.opsForSet();
        stringRedisTemplate.opsForHash();
        stringRedisTemplate.opsForZSet();
        return null;
    }

    public String testObject(){
        //默认如果保存对象，需要将对象序列化，然后使用jdk序列化机制，将序列化之后的数据保存到redis中
        redisTemplate.opsForValue().set("user",new User());
        //使用json保存对象
        myRedisTemplate.opsForValue().set("user",new User());
        return null;
    }
}
