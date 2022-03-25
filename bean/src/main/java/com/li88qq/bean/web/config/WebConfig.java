package com.li88qq.bean.web.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 初始化404配置
 *
 * @author li88qq
 * @version 1.0 2022/3/25 20:54
 */
@Component
public class WebConfig implements BeanPostProcessor {

    @Resource
    private WebMvcProperties webMvcProperties;
    @Resource
    private WebProperties WebProperties;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //404直接抛出异常
        webMvcProperties.setThrowExceptionIfNoHandlerFound(true);
        //不主动配置路由
        WebProperties.getResources().setAddMappings(false);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
