## 一、Spring Boot简介及搭建



### 1.什么是spring boot

​		引用官方的原文“Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can “just run”.”（这段话的意思是spring boot是让开发人员更加简单的去创建一个独立的,产品级的应用，你仅仅只需要去运行就好了）从两个修饰词看springboot其实更偏向于目前的微服务的概念，这个微服务感念可以搜一下martin fowler关于微服务的文章。说到底springboot其实就是一个框架，封装好了以前开发spring项目所有繁琐的配置，让约定大于配置，更简单的去创建和应用一个spring的项目。

### 2.优势

​		1）快速创建独立运行的spring项目和主流框架集成；
​		2）使用嵌入式的servlet容器，部署无需打成war包；
​		3）starter自动依赖与版本控制；
​		4）大量的自动配置，简化开发，也可以修改配置的默认值（yml文件等）；
​		5）无需配置XML，并且无相关代码生成，开箱即用；
​		6）准生产环境的运行时应用监控；
​		7）与云计算的天然集成。



### 3.创建springboot项目（目前自己使用的是IDEA）

**环境要求：
springboot1.2以上至少需要jdk1.7和maven3.2
springboot2.0以上至少需要jdk1.8和maven3.2**









## 十一、SpringBoot与消息（JMS、AMQP、RabbitMQ）



### 1.概述

  1. 大多数引用中，可通过消息中间件来提升系统异步通信、扩展解耦能力；

  2. 消息服务中两个重要概念：

     ​		<font color=#FF0000 >消息代理（message broker）</font>和<font color="red">目的地（distination）</font>

     ​		当消息发送者发送消息以后，将由消息代理接管，消息代理保证消息传递到指定目的地。

  3. 消息队列主要有两种形式的目的地：

     ​		<font color=#FF0000 >队列（queue）</font>：点对点消息通信（point-to-point）

     ​		<font color=#FF0000 >主题（topic）</font>：发布（publish）/订阅（subscribe）消息通信

  4. 应用场景：

       		1.	异步处理（例如用户注册时，需要同时调用发邮件，发短信等服务，可以采用写入消息队列，立即响应用户，比多线程处理的更快）
       		2.	应用解耦（调用多个服务，无需单线程调用，将应用之间的关系解耦，通过消息队列分发）
       		3.	流量削峰（限时秒杀，限制指定数量的数据进行请求，其他的直接发送失败）

  5. 点对点式：

       		1.	消息发送者发送消息，消息代理将其放入一个队列中，消息接收者从队列中获取消息内容，消息读取后被移出队列
       		2.	消息只有唯一的发送者和接收者，但并不是只能有一个接受者。（多个接受者中，有一个人接收了，其他的接受者就不能接收了）

  6. 发布订阅式：

     ​		发布者（发送者）发送消息到主题，多个接收者（订阅者）监听（订阅）这个主题，那么就会在消息到达时同时收到消息。

  7. JMS（Java Message Service）JAVA消息服务：

     ​		基于JVM消息代理的规范。（ActiveMQ、HornetMQ）

  8. AMQP（Advanced Message Queuing Protocol）

     ​		高级消息队列协议，也是一个消息代理的规范，兼容JMS（RabbitMQ）



|              | JMS                                                        | AMQP                                                         |
| ------------ | ---------------------------------------------------------- | ------------------------------------------------------------ |
| 定义         | Java API                                                   | 网络线级协议                                                 |
| 跨语言       | 否                                                         | 是                                                           |
| 跨平台       | 否                                                         | 是                                                           |
| Model        | 提供两种消息模型：<br />（1）Peer-2-Peer<br />（2）Pub/Sub | 提供五种消息模型：<br />（1）direct exchange<br />（2）fanout exchange<br />（3）topic change<br />（4）headers exchange<br />（5）system exchange<br />本质来讲，后四种和JMS的pu |
| 支持消息类型 |                                                            |                                                              |
| 综合评价     |                                                            |                                                              |





