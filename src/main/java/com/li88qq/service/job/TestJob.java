package com.li88qq.service.job;

import com.li88qq.service.utils.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author li88qq
 * @version 1.0 2021/9/15 23:37
 */
//@Component
public class TestJob {

    /**
     * 测试job
     * 表达式: 秒 分 时 日 月 星期 年
     */
    @Scheduled(cron = "0 * * * * ?")
    public void test() {
        LocalDateTime now = LocalDateTime.now();
        String log = String.join("", "测试定时任务-->", DateUtil.format(now, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(log);
    }
}
