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
     
     |              | JMS                                                          | AMQP                                                         |
     | ------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
     | 定义         | Java API                                                     | 网络线级协议                                                 |
     | 跨语言       | 否                                                           | 是                                                           |
     | 跨平台       | 否                                                           | 是                                                           |
     | Model        | 提供两种消息模型：<br />（1）Peer-2-Peer<br />（2）Pub/Sub   | 提供五种消息模型：<br />（1）direct exchange<br />（2）fanout exchange<br />（3）topic change<br />（4）headers exchange<br />（5）system exchange<br />本质来讲，后四种和JMS的pub/sub模型没有太大区别，仅在路由机制上做了更详细的划分； |
     | 支持消息类型 | 多重消息类型：<br />TextMessage<br />MapMessage<br />BytesMessage<br />StreamMessage<br />ObjectMessage<br />Message(只有消息头和属性) | byte[]<br />当实际应用时，有复杂的消息，可以将详细序列化后发送 |
     | 综合评价     | JMS定义了JAVA API层面的标准；在java体系中，多个client均可以通过JMS进行交互，不需要应用修改代码，但是其对跨平台的支持较差。 | AMQP定义了wire-level层的协议标准，天然具有跨平台、跨语言的特性。 |
     
     
     
  9. Spring支持

       - spring-jms提供了对JMS的支持
       - spring-rabbit提供了对AMQP的支持
       - 需要ConnetcionFactory的实现来连接消息代理
       - 提供JmsTemplate、RabbitTemplate来发送消息
       - @JmsListener（JMS）、@RabbitTemplate（AMQP）注解在方法上监听消息代理发布的消息
       - @EnableJms、@EnableRabbit开启支持

  10. SpringBoot 自动配置

       - JmsAutoConfiguration
       - RabbitAutoConfiguration

     

### 2.RabbitMQ简介

**RabbitMQ简介**

RabbitMQ是一个由erlang开发的AMQP（Advanved Message Queue Protocol）的开源实现。



**核心概念**

**<font color="blue">Message</font>**

消息，消息是不具名的，它由消息头和消息体组成。消息体是不透明的，而消息头则由一系列的可选属性组成，这些属性包括routing-key（路由键）、priority（相对于其他消息的优先权）、delivery-mode（指出该消息可能需要持久性存储）等。

**<font color="blue">Publisher</font>**

消息的生产者，也是一个向交换器发布消息的客户端应用程序。

**<font color="blue">Exchange</font>**

交换器，用来接收生产者发送的消息并将这些消息路由给服务器中的队列。

<table><tr><td bgcolor="#ff6666">Exchange有4种类型：direct（默认该类型，点对点的类型）、fanout、topic、和headers（后三种为pub/sub类型），不同类型的Exchange转发消息的策略有所区别。</td></tr></table>

**<font color="blue">Queue</font>**

消息队列，用来保存消息直到发送给消费者。它是消息的容器，也是消息的终点。一个消息可投入一个或多个队列。消息一直在队列里面，等待消费者连接到这个队列将其取走。

**<font color="blue">Binding</font>**

绑定，用于消息队列和交换器之间的关联。一个绑定就是基于路由键将交换器和消息队列连接起来的路由规则，所以可以将交换器理解成一个由绑定构成的路由表。

Exchange和Queue的绑定是多对多的关系。

**<font color="blue">Connection</font>**

网络连接，比如一个TCP连接。

**<font color="blue">Channel</font>**

信道，多路复用连接中的一条独立的双向数据流通道。信道是建立在真实的TCP连接内的虚拟连接，AMQP命令都是通过信道发送出去的，不管是发布消息、订阅队列还是接收消息，这些动作都是通过信道完成的。因为对于操作系统来说建立和销毁TCP都是非常昂贵的开销，所以引入了信道的概念，以复用一条TCP连接。

**<font color="blue">Consumer</font>**

消息的消费者，表示一个从消息队列中取得消息的客户端应用程序。

系统来说建立和销毁TCP都是非常昂贵的开销，所以引入了信道的概念，以复用一条TCP连接。

**<font color="blue">Virtual Host</font>**

虚拟主机，表示一批交换器、消息队列的相关对象。虚拟主机是共享相同的身份认证和加密环境的独立服务器域。每个vhost本质上就是一个mini版的RabbitMQ服务器，拥有自己的队列、交换器、绑定和权限机制。vhost是AMQP概念的基础，必须在连接时指定，RabbitMQ默认的vhost是/。

系统来说建立和销毁TCP都是非常昂贵的开销，所以引入了信道的概念，以复用一条TCP连接。

**<font color="blue">Broker</font>**

表示消息队列服务器实体

**<font color="red">流程</font>**

publisher（消息生产者）生成消息发送给break（消息队列服务器实体）中的一个vhost（virtual host，虚拟主机），vhost将这条消息交给指定的exchange（交换器），banding（绑定关系）通过路由键来找到exchange绑定的queue将消息放入消息队列中。consumer（消息的消费者）通过建立一条TCP连接连接queue，开辟多条channel，从queue中获取消息。



### 3.RabbitMQ运行机制

**Exchange类型**

Exchange分发消息时根据类型的不同分发策略也有所区别，目前共四种类型：<font color="red">direct、fanout、topic、headers</font>。headers匹配AMQP消息的header而不是路由键，headers交换器和direct交换器匹配策略完全一致（点对点），但性能差很多，目前几乎用不到了，所以可以忽略。

**<font color="red">Direct Exchange</font>**

消息中的路由键（routing key）如果和banding中的banding key一致，交换器就将消息发到对应的队列中。路由键与队列名完全匹配，如果一个队列绑定到交换机要求路由键为“hello”，则只转发routing key标记为“hello”的消息（完全匹配）。它是完全匹配、单播的模式。

**<font color="red">Fanout Exchange</font>**

每个发到fanout类型交换器的消息都会分到所有绑定的队列上去。fanout交换器不处理路由键，只是简单的将队列绑定到交换器上，每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上。很像子网广播，每台子网内的主机都获得了一份复制的消息。fanout类型转发消息是最快的。

**<font color="red">Topic Exchange</font>**

topic交换器通过模式（模糊）匹配分配消息的路由键属性，将路由键和某个模式进行匹配，此时队列需要绑定到一个模式上。它将路由器和绑定键的字符串切分成单词，这些**单词之间用点隔开**。它同样也会识别两个通配符：符号“#”和符号“\*”。#匹配0个或多个单词，\*匹配一个单词。<font color="red">（PS：模糊匹配是单词级别的）</font>



### 4.RabbitMQ整合

  1. 引入spring-boot-starter-amqp

     ```yml
     
     ```

     

  2. application.yml配置

  3. 测试RabbitMQ：

       - AmqpAdmin：管理组件
       - RabbitTemplate：消息发送处理组件