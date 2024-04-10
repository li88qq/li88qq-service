package com.li88qq.service.config.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author li88qq
 * @version 1.0 2023/12/30 20:22
 */
@Component
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass) {
        return BeanUtil.applicationContext.getBean(tClass);
    }

    public static <T> T getBeanByName(String name,Class<T> tClass) {
        return BeanUtil.applicationContext.getBean(name, tClass);
    }
}
