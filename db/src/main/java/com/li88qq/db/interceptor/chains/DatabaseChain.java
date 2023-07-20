package com.li88qq.db.interceptor.chains;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据切换处理
 *
 * @author li88qq
 * @version 1.0 2023/3/1 22:09
 */
@Component
public class DatabaseChain implements InterceptorChain{

    @Override
    public boolean execute(StatementHandler handler, Method method) {
        return true;
    }
}
