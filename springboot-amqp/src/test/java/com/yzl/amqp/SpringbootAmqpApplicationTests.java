package com.yzl.amqp;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootAmqpApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	/**
	 * 所有的delare都是创建
	 * 所有的delete/remove是删除
	 */
	@Test
	void createExchange(){
		//创建交换器
		amqpAdmin.declareExchange(new DirectExchange("amqp.exchange"));
		//创建消息队列
		amqpAdmin.declareQueue(new Queue("amqp.queue"));
		//绑定交换器和消息队列
		amqpAdmin.declareBinding(new Binding("amqp.queue", Binding.DestinationType.QUEUE,
				"amqp.exchange","hello.amqp",null));
	}

	/**
	 * 1.单播（点对点）
	 */
	@Test
	void contextLoads() {
		//Message需要自己构造一个，定义消息体和消息头
		//rabbitTemplate.send(exchange,routeKey,message);

		//object默认作为消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
		//rabbitTemplate.convertAndSend(exchange,routeKey,object);
		Map<String,Object> map = new HashMap<>();
		map.put("title","这是一个内容的主题");
		map.put("context", Arrays.asList("第一个数据","第二个数据"));
		//对象被序列化后发送
		rabbitTemplate.convertAndSend("directEx","hello.new",map);
	}

	@Test
	void receive(){
		Object o = rabbitTemplate.receiveAndConvert("hello.new");
		System.out.println(o.getClass());
		System.out.println(o);
	}

}
