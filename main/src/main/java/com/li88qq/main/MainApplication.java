package com.li88qq.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author li88qq
 * @version 1.0 2021/12/17 22:53
 */
@SpringBootApplication(scanBasePackages = {"com.li88qq.main", "com.li88qq.db.core", "com.li88qq.bean.web"})
@EnableDiscoveryClient
@MapperScan(basePackages = "com.li88qq.main.dao")
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
