package com.zyj.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zyj.spring.boot.scheduler.CronSchedulerJob;

@Component
public class MyStartupRunner implements CommandLineRunner {

    @Autowired
    public CronSchedulerJob scheduleJobs;

    @Override
    public void run(String... args) throws Exception {
        scheduleJobs.scheduleJobs();
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
    }
}