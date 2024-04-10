package com.li88qq.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author lm
 * @version 1.0 2024/4/10 22:58
 */
@SpringBootApplication(scanBasePackages = {"com.li88qq.db","com.li88qq.service"})
@EnableScheduling
@MapperScan(basePackages = {"com.li88qq.db.dao","com.li88qq.service.dao"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
