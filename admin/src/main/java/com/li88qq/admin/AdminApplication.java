package com.li88qq.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author li88qq
 * @version 1.0 2023/7/19 22:15
 */
@SpringBootApplication(scanBasePackages = {"com.li88qq.db","com.li88qq.admin","com.li88qq.common"})
@MapperScan(basePackages = {"com.li88qq.db","com.li88qq.admin.dao"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
