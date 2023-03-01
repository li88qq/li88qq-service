package com.li88qq.db.interceptor.chains;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 动态条件
 *
 * @author li88qq
 * @version 1.0 2023/3/1 22:07
 */
@Component
public class ConditionChain implements InterceptorChain{

    @Override
    public boolean execute(StatementHandler handler, Method method) {
        System.out.println(this.getClass());
        return true;
    }
}
