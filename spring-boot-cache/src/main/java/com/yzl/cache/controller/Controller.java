package com.yzl.cache.controller;

import com.yzl.cache.mapper.UserMapper;
import com.yzl.cache.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CacheConfig：配置这个类中所有的cacheNames、CacheManager等属性
 * 行内优先级高于@CacheConfig
 */
@CacheConfig(cacheNames = "user")
@RestController
public class Controller {

    @Autowired
    UserMapper userMapper;

    /**
     * 将方法的运行结果进行缓存，以后要用相同的数据，直接从缓存中获取，不再调用方法
     *
     * CacheManager管理多个Cache组件的，对缓存的真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一的名字；
     * Cache的几个属性：
     *      1.cacheName/value：指定缓存组件的名字,将方法的返回值放入哪个缓存中，值是一个数组，可以放到多个缓存中
     *      2.key：缓存数据使用的key；默认是使用方法参数的值；也可以使用SpEL语法编写，#id、#a0、#p0、#root.args[0]
     *              例如  key = "#root.methodName+'['+#a0+']'"
     *      3.keyGenerator：key的生成器，可以自己指定key的生成器的组件id
     *          **key和keyGenerator二选一使用
     *      4.cacheManager：指定缓存管理器，或者使用cacheResolver指定缓存解析器
     *      5.condition：指定符合条件的情况下才缓存
     *      6.unless：指定条件不满足时才缓存
     *          **condition和unless可以同时使用
     *      7.sync：是否使用异步模式
     * 源码分析（原理）：
     *     1、自动配置类：CacheAutoConfiguration
     *     2、缓存的配置类
     *       0 = "org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration"
             1 = "org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration"
             2 = "org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration"
             3 = "org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration"
             4 = "org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration"
             5 = "org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration"
             6 = "org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration"
             7 = "org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration"
             8 = "org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration"
             9 = "org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration"
           3、哪个配置类默认生效：SimpleCacheConfiguration
           4、给容器中注册一个CacheManager：ConcurrentMapCacheManager
           5、可以获取和创建ConcurrentMapCache类型的缓存组件，它的作用是将数据保存在ConcurrentMap中

     * 运行流程：（以@Cacheable为例）
     *  1、方法运行之前，先去查询cache（缓存组件），按照cacheNames指定的名字获取；
     *      （CacheManager先获取响应的缓存），第一次获取缓存如果没有cache组件会自动创建cache和map
     *  2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数
     *      key是按照某种策略生成的，默认是使用keyGenerator生成的，默认的keyGenerator是SimpleKeyGenerator
     *      SimpleKeyGenerator生成key的策略：
     *              如果没有参数：key=new SimpleKey();
     *              如果有一个参数：key=参数的值
     *              如果有多个参数：key=new SimpleKey(参数集合);
     *      自定义keyGenerator：写一个keyGenerator加入容器，并把bean id配置@Cacheable中
     *  3、没有查到缓存就调用目标方法
     *  4、将目标方法返回的结果，放进缓存中
     *
     *  流程总结：@Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     *          如果没有就运行方法并将结果放入缓存，以后再来调用就可以直接使用缓存中的数据。
     *
     *  核心：
     *      1）使用CacheManager（ConcurrentMapCacheManager）按照名字获得Cache（ConcurrentMap）组件
     *      2）key使用keyGenerator生成的，默认的keyGenerator是SimpleKeyGenerator
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    @Cacheable(cacheNames = "user1" , /*keyGenerator = "myKeyGenerator",*/key="#id",
            condition = "#a0 > 0 and #root.methodName eq 'getById'",
            unless = "#a0 == 3")
    public User getById(@PathVariable("id") Integer id){
        return userMapper.getById(id);
    }


    /**
     * @CachePut 既调用方法，又更新缓存数据(同步更新缓存)
     * 修改了数据库的某个方法，同时更新缓存
     * 运行时机：
     *      1、先调用目标方法
     *      2、将目标方法返回的结果缓存起来
     *
     *  更新时需要指定key与插入时的key相同：
     *      1、key=“#object.property”（key = "#user.id"）
     *      2、key="#result.id"（result表示返回值的对象，因为CachePut是在方法调用完之后执行，所以可以取到返回值）
     * @param user
     * @return
     */
    @CachePut(cacheNames = "user",key = "#user.id")
    @RequestMapping("/user")
    public User updateById(User user){
        userMapper.updateById(user);
        return user;
    }

    /**
     * @CacheEvict：清除缓存
     *      key：指定要清除的数据
     *      allEntries：清除缓存中的所有数据，默认是false
     *      beforeInvocation：清除缓存是否在方法执行之前执行，默认是false
     * @param id
     * @return
     */
    @CacheEvict(cacheNames = "user",allEntries = true/*,key = "#id"*/)
    @GetMapping("deleteUser")
    public String deleteUser(Integer id){
        userMapper.deleteById(id);
        return "success";
    }

    /**
     * @Caching：包含多个@Cacheable、@CachePut、@CacheEvict，定义复杂的缓存规则
     *  运用在比较复杂的，需要使用多种缓存的场景
     * @param pass
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(cacheNames = "user",key = "#pass")
            },
            put = {
                    @CachePut(cacheNames = "user",key = "#result.id"),
                    @CachePut(cacheNames = "user",key = "#result.sname")
            }
    )
    @GetMapping("queryByPass")
    public User queryByPass(String pass){
        return userMapper.queryByPass(pass);
    }
}
