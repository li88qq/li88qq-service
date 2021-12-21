package com.li88qq.db.config;

import com.li88qq.db.interceptor.MybatisInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis定义bean
 *
 * @author li88qq
 * @version 1.0 2021/12/15 23:35
 */
@Configuration
public class MyBatisBean {

    /**
     * mybatis拦截器
     *
     * @return
     */
    @Bean
    public MybatisInterceptor mybatisInterceptor() {
        MybatisInterceptor interceptor = new MybatisInterceptor();
        return interceptor;
    }
}
