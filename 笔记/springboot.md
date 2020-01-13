# 一、Spring Boot简介及搭建



## 1.什么是spring boot

​		引用官方的原文“Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can “just run”.”（这段话的意思是spring boot是让开发人员更加简单的去创建一个独立的,产品级的应用，你仅仅只需要去运行就好了）从两个修饰词看springboot其实更偏向于目前的微服务的概念，这个微服务感念可以搜一下martin fowler关于微服务的文章。说到底springboot其实就是一个框架，封装好了以前开发spring项目所有繁琐的配置，让约定大于配置，更简单的去创建和应用一个spring的项目。


## 2.优势

​		1）快速创建独立运行的spring项目和主流框架集成；
​		2）使用嵌入式的servlet容器，部署无需打成war包；
​		3）starter自动依赖与版本控制；
​		4）大量的自动配置，简化开发，也可以修改配置的默认值（yml文件等）；
​		5）无需配置XML，并且无相关代码生成，开箱即用；
​		6）准生产环境的运行时应用监控；
​		7）与云计算的天然集成。



## 3.创建springboot项目（目前自己使用的是IDEA）



**环境要求：
springboot1.2以上至少需要jdk1.7和maven3.2
springboot2.0以上至少需要jdk1.8和maven3.2**