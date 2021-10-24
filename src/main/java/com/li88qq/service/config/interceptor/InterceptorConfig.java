package com.li88qq.service.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局拦截器
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Resource
    private LoginInterceptor loginInterceptor;

    /**
     * 拦截器配置
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludePath());
        super.addInterceptors(registry);
    }

    /**
     * 不包括的路径
     *
     * @return
     */
    private String[] excludePath() {
        List<String> paths = new ArrayList<>();
        paths.add("/p/**");
        paths.add("/login");
        paths.add("/loginMobile");
        return paths.toArray(new String[paths.size()]);
    }
}
