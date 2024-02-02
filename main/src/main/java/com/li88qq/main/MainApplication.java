package com.li88qq.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author li88qq
 * @version 1.0 2023/12/26 21:53
 */
@SpringBootApplication(scanBasePackages = {"com.li88qq.main","com.li88qq.publics","com.li88qq.db"})
@EnableScheduling
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.li88qq.main.dao","com.li88qq.db"})
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
