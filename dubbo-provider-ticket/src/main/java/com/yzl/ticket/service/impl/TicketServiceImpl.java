package com.yzl.ticket.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yzl.ticket.service.TicketService;
import org.springframework.stereotype.Component;

@Component
@Service(version = "1.0.0")
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "获得一张票";
    }
}
