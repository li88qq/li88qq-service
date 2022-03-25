package com.li88qq.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 登录管理
 *
 * @author li88qq
 * @version 1.0 2022/1/3 23:06
 */
@SpringBootApplication(scanBasePackages = {"com.li88qq.login","com.li88qq.db.core","com.li88qq.bean.web"})
@EnableDiscoveryClient
@MapperScan(basePackages = "com.li88qq.login.dao")
public class LoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }
}
