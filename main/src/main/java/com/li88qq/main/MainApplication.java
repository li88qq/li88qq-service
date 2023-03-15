package com.li88qq.main;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author li88qq
 * @version 1.0 2021/12/17 22:53
 */
@SpringBootApplication(scanBasePackages = {"com.li88qq.main", "com.li88qq.db", "com.li88qq.bean.web"})
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.li88qq.main.dao", "com.li88qq.db"})
@EnableAutoDataSourceProxy
@EnableFeignClients
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
