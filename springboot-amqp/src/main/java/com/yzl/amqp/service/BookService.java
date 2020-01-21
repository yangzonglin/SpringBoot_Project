package com.yzl.amqp.service;

import com.yzl.amqp.pojo.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author yzl
 * @Create 2020/1/21
 */
@Service
public class BookService {

    /**
     * 监听队列获取消息体对象
     * @param book
     */
    @RabbitListener(queues = "amqp.queue")
    public void receiveBook(Book book){
        System.out.println(book);
    }

    /**
     * 监听队列获取消息体和消息头
     * @param message
     */
    @RabbitListener(queues = "amqp.queue")
    public void receiveMessage(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
