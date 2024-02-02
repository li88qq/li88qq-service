package com.li88qq.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author li88qq
 * @version 1.0 2023/12/26 22:59
 */
@SpringBootApplication(scanBasePackages = {"com.li88qq.login","com.li88qq.publics","com.li88qq.db"})
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.li88qq.login.dao","com.li88qq.db"})
public class LoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class,args);
    }
}
