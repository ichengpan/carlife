package com.iviui.carlife.modules.quartz;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: ChengPan
 * @date: 2019/5/21
 * @description: 定时器
 */

@Component
@EnableScheduling
public class QuartzTask {

    @Scheduled(cron = "0 0/10 * * * ?")//http://cron.qqe2.com/表达式生成网址
    public void schTest1() {
        Date date = new Date();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sim.format(date);
        System.out.println("这是spring定时器，每十分钟执行一次,当前时间：" + dateStr);
    }
}