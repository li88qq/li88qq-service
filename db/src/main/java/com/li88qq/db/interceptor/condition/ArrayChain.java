package com.li88qq.db.interceptor.condition;

import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 列表
 *
 * @author li88qq
 * @version 1.0 2023/3/5 20:23
 */
@Component
public class ArrayChain implements NodeChain {

    @Override
    public boolean check(Class<?> aClass) {
        return aClass == Collection.class || aClass.isArray();
    }
}
