package com.li88qq.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author li88qq
 * @version 1.0 2023/10/14 22:32
 */
@SpringBootApplication(scanBasePackages = {"com.li88qq.db","com.li88qq.main","com.li88qq.common"})
@MapperScan(basePackages = {"com.li88qq.db","com.li88qq.main.dao"})
@EnableDiscoveryClient
@IntegrationComponentScan
@EnableScheduling
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
