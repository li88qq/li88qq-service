package com.li88qq.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author li88qq
 * @version 1.0 2023/12/16 11:00
 */
@SpringBootApplication(scanBasePackages = {"com.li88qq.gateway", "com.li88qq.publics.common"})
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
