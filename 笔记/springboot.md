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

​     

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
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-amqp</artifactId>
     </dependency>
     ```

     

  2. application.yml配置

       ```yml
       #用户名密码默认为guest，端口也是默认的
       spring.rabbitmq.host=106.12.189.90
       ```

       

  3. 测试RabbitMQ：

       - AmqpAdmin：管理组件
       - RabbitTemplate：消息发送处理组件



## 十二、SpringBoot 与 检索（ElasticSearch）

### 1、检索

我们的应用经常需要添加检索功能，开源的ElasticSearch是目前全文搜索引擎的首选。他可以快速的存储、搜索和分析海量数据。SpringBoot通过整合Spring Data ElasticSearch为我们提供了非常便捷的检索功能支持。

ElasticSearch是一个分布式搜索服务，提供的Restful API，底层基于Lucene，采用多shard（分片）的方式保证数据安全，并且提供自动resharding的功能，github等大型的站点也是采用了ElasticSearch作为其搜索服务。

**docker运行ElasticSearch： **

​	docker run -e ES_JAVA_OPTS="-Xms256m -Xmx256m" -d -p 9200:9200 -p 9300:9300 --name myES 7516701e4922

<table><tr><td bgcolor="#ff6666">docker启动默认占用内存为2G，所以根据自身服务器可以更改启动的内存占用：-e ES_JAVA_OPTS="-Xms256m -Xmx256m"。9200端口是ES进行web通信的端口，9300是ES分布式情况下各个节点之间的通信端口。</td></tr></table>
### 2、概念

* 以员工文档的形式存储为例：一个<font color=red>文档</font>代表一个员工数据。存储数据到ElasticSearch的行为叫做<font color=red>索引</font>，但在索引一个文档之前，需要确定将文档存储在哪里。

* 一个ElasticSearch集群可以包含多个<font color=red>索引</font>，相应的每个索引可以包含多个<font color=red>类型</font>。这些不同的类型存储着多个<font color=red>文档</font>，每个文档又有多个<font color=red>属性</font>。

* 类似关系：
  - 索引-数据库
  - 类型-表
  - 文档-表中的记录
  - 属性-列
  
* RESTFUL API：

  将 HTTP 命令由 `PUT` 改为 `GET` 可以用来检索文档，同样的，可以使用 `DELETE` 命令来删除文档，以及使用 `HEAD` 指令来检查文档是否存在。如果想更新已存在的文档，只需再次 `PUT` 。(第一次put数据是insert，第二次put相同id的数据则为update)

### 3、应用

```java
SpringBoot默认支持3种技术来和ES交互（1.X为2种，2,X新增RestClient方式）
1.Jest（默认不生效）
     需要导入jest的工具包：（大版本号需要和ES的大版本号相同）
         <dependency>
            <groupId>io.searchbox</groupId>
            <artifactId>jest</artifactId>
            <version>5.3.4</version>
        </dependency>
 2.SpringData ElasticSearch[ES版本需要和springdata的es版本对应]
     （PS：如果遇到不适配情况，可以通过两个方式解决：1.升级springboot版本2.安装对应版本的ES）
     https://docs.spring.io/spring-data/elasticsearch/docs/3.2.0.RC3/reference/html/#preface.versions
     1）Client：节点信息 clusterNodes、clusterName
     2）ElasticSearchTemplate操作ES
     3）编写一个ElasticsearchRepository的子接口来操作ES
 3.RestClient(Java High Level REST Client)操作ES
```

​		1.使用jest是用JestClient来操作ES，标记索引id用@JestId

​		2.使用SpringData ElasticSearch操作ES，需要在实体上添加注解@Document(indexName = "",type = "")指定索引名和类型名，然后创建该实体类型的repository接口，继承ElasticsearchRepository接口（泛型为实体类及其主键类型），通过该repository来操作ES。



## 十三、SpringBoot与任务

### 1、异步任务

让方法异步执行：

在方法上加注解@Async，并在主程序类上添加注解@EnableAsync即可。

### 2、定时任务

项目开发中经常需要执行一些定时任务，比如需要在每天凌晨的时候，分析一次前一天的日志信息。Spring为我们提供了异步执行任务调度的方式，提供TaskExecutor、TaskSchedule接口。

<b><font style="color:blue">两个注解：</font></b>@EnableScheduling、@Scheduled

<b><font style="color:blue">cron表达式：</font></b>

Cron表达式由7个部分组成，各部分用空格隔开，Cron表达式的7个部分从左到右代表的含义如下：

```
秒 分 时 日 月 周 年
```

| 字段                     | 允许值                                                       | 允许的特殊字符             |
| ------------------------ | ------------------------------------------------------------ | -------------------------- |
| 秒（Seconds）            | 0~59的整数                                                   | , - * /   四个字符         |
| 分（*Minutes*）          | 0~59的整数                                                   | , - * /   四个字符         |
| 小时（*Hours*）          | 0~23的整数                                                   | , - * /   四个字符         |
| 日期（*DayofMonth*）     | 1~31的整数（但是你需要考虑你月的天数）                       | ,- * ? / L W C   八个字符  |
| 月份（*Month*）          | 1~12的整数或者 JAN-DEC                                       | , - * /   四个字符         |
| 星期（*DayofWeek*）      | <font style="color:red">0~7的整数或者 SUN-SAT （0和7=SUN），<br />在Quartz中为1-7,1表示周天</font> | , - * ? / L C #   八个字符 |
| 年(可选，留空)（*Year*） | 1970~2099                                                    | , - * /   四个字符         |

<b><font style="color:blue">cron表达式符号说明：</font></b>

- `,`：表示列出枚举值值。例如在`分`使用5,20，则意味着在5和20分每分钟触发一次。
- `-`：表示范围。例如在`分`使用5-20，表示从5分到20分钟每分钟触发一次。
- `*` ：表示匹配该域的任意值。假如在`分`域使用`*`，即表示每分钟都会触发事件。
- `/` ：表示起始时间开始触发，然后每隔固定时间触发一次，例如在Minutes域使用5/20,则意味着从5分钟开始每20分钟触发一次，即5，25，45,分别触发一次。
- `?` ：只能用在`周`和`日`。它也匹配域的任意值，但实际不会。因为`周`和`日`会相互影响。例如想在每月的20日触发调度，不管20日到底是星期几，则只能使用如下写法： 13 13 15 20 * ?,其中最后一位只能用？，而不能使用*，如果使用*表示不管星期几都会触发，实际上并不是这样。
- `L` ： 表示最后，只能出现在`日`和`周`，如果在`日`使用5L,意味着在最后的一个星期四触发。
- `W`：表示有效工作日(周一到周五),只能出现在`周`域，系统将在离指定日期的最近的有效工作日触发事件。例如：在`日`使用5W，如果5日是星期六，则将在最近的工作日：星期五，即4日触发。如果5日是星期天，则在6日触发；如果5日在星期一到星期五中的一天，则就在5日触发。另外一点，W的最近寻找不会跨过月份。
- `#`：用于确定每个月第几个星期几，只能出现在`周`。例如在4#2，表示某月的第二个星期三。

<b><font style="color:blue">cron表达式范例：</font></b>

- `*/5 * * * * ?` ：每隔5秒执行一次
- `0 */1 * * * ?` ：每隔1分钟执行一次
- `0 0 23 * * ?` ：每天23点执行一次
- `0 0 1 * * ?` ：每天凌晨1点执行一次：
- `0 0 1 1 * ?` ：每月1号凌晨1点执行一次
- `0 0 23 L * ?` ： 每月最后一天23点执行一次
- `0 0 1 ? * L` ：每周星期天凌晨1点实行一次
- `0 26,29,33 * * * ?` ： 在26分、29分、33分执行一次
- `0 0 0,13,18,21 * * ?` ： 每天的0点、13点、18点、21点都执行一次

<b><font style="color:blue">代码中应用：</font></b>

主配置类上添加注解@EnableScheduling开启定时任务功能，在需要定时任务的方法上添加注解@Scheduled(cron = "50/9 * * * * ?")并编写cron表达式。

### 3、邮件任务

* 邮件发送需要引入spring-boot-starter-mail

* Spring Boot自动配置MailSenderAutoConfiguration

* 定义MailProperties内容，配置在application.yml中

  ```yml
  spring.mail.username=981768766@qq.com
  #在邮箱设置POP3/SMTP服务开启，并生成授权码，授权码即该密码设置
  spring.mail.password=vjfeyemqlsjgbcdj
  spring.mail.host=smtp.qq.com
  
  #邮箱需要ssl安全登陆，所以需要开启smtp的ssl，额外的属性都在spring.mail.properties中配置
  spring.mail.properties.mail.smtp.ssl.enable=true
  ```

* 自动装配JavaMailSender

  ```java
  @Autowired
  JavaMailSenderImpl javaMailSenderImpl;
  
  //测试普通邮件
  @Test
  void contextLoads() {
      //简单邮件对象
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      //邮件设置
      simpleMailMessage.setSubject("通知：12点吃饭");//邮件标题
      simpleMailMessage.setText("到12点吃饭啦");//邮件内容，默认非html格式内容
      simpleMailMessage.setTo("404230554@qq.com");//发送给哪些邮箱
      simpleMailMessage.setFrom("981768766@qq.com");//谁发送的
  
      javaMailSenderImpl.send(simpleMailMessage);
  }
  
  //发送复杂邮件，包含附件等
  @Test
  void sendMessage() throws Exception {
      //创建复杂邮件对象
      MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);//true是包含文件上传，即附件
      //使用helper对象进行邮件设置
      mimeMessageHelper.setSubject("通知：12点吃饭");//邮件标题
      mimeMessageHelper.setText("<b style='color:red'>到12点吃饭啦</b>",true);//邮件内容，默认非html格式内容
      mimeMessageHelper.setTo("3575377587@qq.com");//发送给哪些邮箱
      mimeMessageHelper.setFrom("981768766@qq.com");//谁发送的
      //上传文件
      mimeMessageHelper.addAttachment("1.jpg",new File("E:\\高清图片\\QQ图片20200205173622.jpg"));
      mimeMessageHelper.addAttachment("2.jpg",new File("E:\\高清图片\\QQ图片20200205173643.jpg"));
  
      javaMailSenderImpl.send(mimeMessage);
  }
  ```

* 测试邮件发送



## 十四、SpringBoot与安全（安全、Spring Security）

### 1.概念

* 应用程序的两个主要区域是“认证”和“授权”（或者访问控制）。这两个主要区域是Spring Security的两个目标。
* “认证”（Authentication），是建立一个他声明的主体的过程（一个“主体”一般是指用户，设备或一些可以在你的应用程序中执行动作的其他系统）。
* “授权”（Authorization）指确定一个主体是否允许在你的应用程序执行一个动作的过程。为了抵达需要授权的店，主体的身份已经有认证过程建立。
* 这个概念是通用的而不只在Spring Security中。

### 2.java配置

```java
/**
 * 设置spring security配置
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    //定制请求的授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/vip1","/vip3").hasRole("VIP1").
        antMatchers("/vip2").hasRole("VIP2");

        //开启自动配置的登陆功能,不设置参数为跳转spring security默认的登陆页面（/login）
        /**
         * 1./login来到登录页
         * 2.重定向到/login？error表示登录失败
         * 3.更多详细规则
         * 4.loginPage:自定义登录页地址,配置之后跳转登录页的地址和登录页登录请求地址都为该地址（登录操作spring security自动处理）
         * 5.usernameParameter和passwordParameter：自定义登录页后，修改用户名和密码表单的name值的默认值
         */
        http.formLogin().loginPage("/loginPage").usernameParameter("user").passwordParameter("pwd");

        /**
         * 开启自动配置的注销功能
         * 1.访问/logout表示用户注销，清空session
         * 2.注销成功会返回/login?logout页面
         * 3.logoutSuccessUrl：设置注销成功之后的跳转页面，默认跳转页面为2所述
         */
        http.logout().logoutSuccessUrl("/");

        /**
         * 开启自动配置的记住我
         * 登录成功后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登陆
         * 点击注销会删除cookie（或等到浏览器的cookie到期失效）
         * 1.rememberMeParameter：自定义的登录页面的记住我，设置表单提交的name值
         */
        http.rememberMe().rememberMeParameter("remember");
    }


    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //账号密码匹配存入内存（正式项目应从数据库取值）
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).
                withUser("yzl").password("123456").roles("VIP1","VIP2").
                and().
                withUser("hyj").password("123456").roles("VIP1");
    }
}


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
```

### 3. spring security的sec属性

在页面中使用spring security属性sec：

```html
<!DOCTYPE html>
<html lang="en"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <style>
        div{
            height: 100px;
        }
    </style>
</head>
<body>
<div sec:authorize="isAuthenticated()"><!--判断是否登录-->
    <!--获取登录用户名、角色名-->
    <h1>欢迎<span sec:authentication="name"></span>来到首页,您的权限有：<span sec:authentication="principal.authorities"></span></h1>
    <a th:href="@{/logout}">注销</a>
</div>
<div sec:authorize="!isAuthenticated()">
    <h1>欢迎游客来到首页,<a th:href="@{/loginPage}">请登录</a></h1>
</div>
<!--判断是否有某个角色-->
<div sec:authorize="hasRole('VIP1')">
    <a th:href="@{/vip1}">VIP1</a>
</div>
<div sec:authorize="hasRole('VIP2')">
    <a th:href="@{/vip2}">VIP2</a>
</div>
<div sec:authorize="hasRole('VIP1')">
    <a th:href="@{/vip3}">VIP3</a>
</div>
</body>
</html>
```



## 十五、Spring Boot 与 分布式（分步式、Dubbo/Zookeeper、SpringBoot/Cloud）

### 1.分布式应用

在分布式系统中，国内常用Zookeeper+dubbo组合，而Spring Boot推荐使用全栈的Spring，Spring Boot + Spring Cloud。

分布式系统：

![dubbo1](D:\ProgramCode\SpringBoot\SpringBoot_Study\笔记\images\dubbo1.png)

### 2.Zookeeper和Dubbo

* **Zookeeper**

  Zookeeper是一个分布式的，开放源码的分布式应用程序协调服务。它是一个为分布式应用提供一致性服务的软件，提供的功能包括：配置维护、域名服务、分布式同步、组服务等。

* **Dubbo**

  Dubbo是Alibaba开源的分布式服务框架，它最大的特点是按照分层的方式来架构，使用这种方式可以使各个层之间解耦合（或者最大限度地松耦合）。从服务模型的角度看，Dubbo采用的是一种非常简单的模型，要么是提供方提供服务，要么是消费方消费服务，所以基于这一点可以抽象出服务提供方（Provider）和服务消费方（Customer）两个角色。

  ![dubbo2](D:\ProgramCode\SpringBoot\SpringBoot_Study\笔记\images\dubbo2.png)

| 节点      | 说明                                   |
| --------- | -------------------------------------- |
| Text      | Text                                   |
| Provider  | 暴露服务的服务提供方                   |
| Consumer  | 调用远程服务的服务消费方               |
| Registry  | 服务注册与发现的注册中心               |
| Monitor   | 统计服务的调用次数和调用时间的监控中心 |
| Container | 服务运行容器                           |

### 3.调用关系说明

服务容器负责启动，加载，运行服务提供者。
服务提供者在启动时，向注册中心注册自己提供的服务。
服务消费者在启动时，向注册中心订阅自己所需的服务。
注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。

### 4.springboot整合dubbo

springboot1.X和2.X整合dubbo主要在pom.xml中区别较大：

在2.X中的pom.xml：

```xml
<!-- 升级 apache dubbo -->
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo</artifactId>
    <version>2.7.3</version>
</dependency>
<!-- Zookeeper -->
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.5.3-beta</version>
</dependency>
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
    <version>4.2.0</version>
</dependency>
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-recipes</artifactId>
    <version>4.2.0</version>
</dependency>
<!-- 最新 starter -->
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>2.7.3</version>
</dependency>
```

在1.X中的pom：

```xml
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>0.1.2.RELEASE</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.github.sgroschupf/zkclient -->
<dependency>
    <groupId>com.github.sgroschupf</groupId>
    <artifactId>zkclient</artifactId>
    <version>0.1</version>
</dependency>
```

在2.X中使用注解@Reference和@Service都需要给值version = "1.0.0"；

其中@Service在提供者的service上加，@Reference在消费者上加。

需要配置的基础项有以下三个，其中扫描包在消费者中不配置：

```properties
#配置服务名称
dubbo.application.name=dubbo-provider-ticket
#配置服务注册（zk）的地址
dubbo.registry.address=zookeeper://106.12.189.90:2181
#配置需要注册服务的包名
dubbo.scan.base-packages=com.yzl.ticket.service
```

在消费者中，需要服务全包名的接口引用（保证两个项目com.xxx相同），然后用@Reference来进行引用服务。

### 5.SpringBoot和SpringCloud

**SpringCloud**

SpringCloud是一个分布式的整体解决方案。SpringCloud为开发者提供了**在分布式系统（配置管理，服务发现，熔断，路由，微代理，控制总线，一次性token，全局锁，leader选举，分布式session，集群状态）中快速构建的工具，**使用SpringCloud的开发者可以快速的启动服务或构建应用、同时能够快速和云平台资源进行对接。

* SpringCloud分布式开发五大常用组件
  * 服务发现——Netflix Eureka
  * 客户端负载均衡——Netflix Ribbon
  * 熔断器——Netflix Hystrix
  * 服务网关——Netflix Zuul
  * 分布式配置——Spring Cloud Config

eureka注册中心配置：

```yml
server:
  port: 8761
eureka:
  instance:
    hostname: springcloud-eureka-service  #eureka实例的主机名
  client:
    register-with-eureka: false #不把自己注册到eureka上
    fetch-registry: false #不从eureka上获取服务的注册信息
    service-url:  #注册中心的地址：key-value组成
      defaultZone: http://localhost:8761/eureka/
```

eureka服务提供配置：

```yml
server:
  port: 8001
spring:
  application:
    name: springcloud-eureka-provider
eureka:
  instance:
    prefer-ip-address: true   #注册服务时候使用服务的ip地址
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/  #注册中心地址
```

eureka消费者配置：

```yml
server:
  port: 8100
spring:
  application:
    name: springcloud-eureka-customer
eureka:
  instance:
    prefer-ip-address: true   #注册服务时候使用服务的ip地址
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/  #注册中心地址
```

消费者使用RestTemplate调用服务：

```java
@EnableDiscoveryClient  //开启服务发现的功能
@SpringBootApplication
public class SpringcloudEurekaCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaCustomerApplication.class, args);
    }

    @LoadBalanced   //（发送http请求）启用负载均衡机制
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

