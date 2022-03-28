package com.li88qq.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * gateway
 *
 * @author li88qq
 * @version 1.0 2022/1/12 22:46
 */
@SpringBootApplication(scanBasePackages = {"com.li88qq.gateway", "com.li88qq.db.core",
        "com.li88qq.bean.web.session","com.li88qq.bean.web.redis"})
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
