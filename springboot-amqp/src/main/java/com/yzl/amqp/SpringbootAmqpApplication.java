package com.yzl.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 * 1、RabbitAutoConfiguration
 * 2、有自动配置了连接工厂rabbitConnectionFactory
 * 3、RabbitProperties封装了RabbitMQ的配置
 * 4、RabbitTemplate：给RabbitMQ发送和接收消息
 * 5、AmqpAdmin：RabbitMQ系统管理功能组件（创建交换器、队列等）
 */
@SpringBootApplication
public class SpringbootAmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAmqpApplication.class, args);
	}

}
