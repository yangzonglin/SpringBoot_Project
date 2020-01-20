package com.yzl.amqp;

import org.junit.jupiter.api.Test;
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
		rabbitTemplate.convertAndSend("directEx","hello.new",map);
	}

	@Test
	void receive(){
		Object o = rabbitTemplate.receiveAndConvert("hello.new");
		System.out.println(o.getClass());
		System.out.println(o);
	}

}
