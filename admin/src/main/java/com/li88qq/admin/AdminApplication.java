package com.li88qq.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 后台管理
 *
 * @author li88qq
 * @version 1.0 2022/3/28 17:45
 */
@SpringBootApplication(scanBasePackages = {"com.li88qq.admin", "com.li88qq.db", "com.li88qq.bean.web"})
@EnableDiscoveryClient
@MapperScan(basePackages = "com.li88qq.admin.dao")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
