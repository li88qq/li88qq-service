package com.li88qq.db.interceptor.condition;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 时间戳
 *
 * @author li88qq
 * @version 1.0 2023/3/5 20:23
 */
@Component
public class TimestampChain implements NodeChain {

    @Override
    public boolean check(Class<?> aClass) {
        return aClass == LocalDate.class || aClass == LocalDateTime.class;
    }
}
