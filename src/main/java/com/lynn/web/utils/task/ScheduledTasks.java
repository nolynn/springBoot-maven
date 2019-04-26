package com.lynn.web.utils.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 在application 中加入 @EnableScheduling 注解
 * fixedRate：5000上一次开始执行时间点之后5秒再执行
 * fixedDelay=5000 上一次执行结束时间点后5秒执行
 * initialDelay=1000 第一次延迟一秒
 * cron 按照cron表达式执行 类似 quratz
 *
 * @Description:
 * @Date: 2019/4/26 11:14
 * @Auther: lynn
 */
@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
