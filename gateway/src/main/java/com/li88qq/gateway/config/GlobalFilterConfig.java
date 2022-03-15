package com.li88qq.gateway.config;

import com.li88qq.gateway.filter.LogFilter;
import com.li88qq.gateway.filter.SessionFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局过滤器
 *
 * @author li88qq
 * @version 1.0 2022/1/15 22:58
 */
@Configuration
public class GlobalFilterConfig {

    @Bean
    public GlobalFilter sessionFilter() {
        SessionFilter filter = new SessionFilter(1);
        return filter;
    }

    @Bean
    public GlobalFilter logFilter() {
        LogFilter filter = new LogFilter(2);
        return filter;
    }
}
