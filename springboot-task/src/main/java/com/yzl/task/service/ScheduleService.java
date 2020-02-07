package com.yzl.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Scheduled(cron = "50/9 * * * * ?")
    public void doSchedule(){
        System.out.println("执行定时任务.......");
    }
}
