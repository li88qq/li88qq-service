package com.li88qq.db.interceptor.condition;

import org.springframework.stereotype.Component;

/**
 * 字符串
 *
 * @author li88qq
 * @version 1.0 2023/3/5 20:22
 */
@Component
public class StringChain implements NodeChain {

    @Override
    public boolean check(Class<?> aClass) {
        return aClass == String.class;
    }
}
