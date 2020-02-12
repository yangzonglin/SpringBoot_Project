package com.yzl.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yzl.ticket.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Reference(version = "1.0.0")
    TicketService ticketService;

    public String hello(){
        return ticketService.getTicket();
    }
}
