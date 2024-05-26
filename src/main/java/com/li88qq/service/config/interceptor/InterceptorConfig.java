package com.li88qq.service.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截配置
 *
 * @author lm
 * @version 1.0 2024/5/26 16:10
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePaths());
    }

    /**
     * 白名单
     * @return 拦截白名单
     */
    private String[] excludePaths() {
        return new String[]{
                "/login",
                "/logout"
        };
    }
}
