package com.li88qq.db.interceptor;

import com.li88qq.db.interceptor.plugins.StatementInterceptor;
import jakarta.annotation.Resource;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * 拦截器
 *
 * @author li88qq
 * @version 1.0 2023/2/28 22:10
 */
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MybatisInterceptor implements Interceptor {

    @Resource
    private StatementInterceptor statementInterceptor;

    /**
     * 拦截处理
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (target instanceof StatementHandler) {
            return statementInterceptor.invoke(invocation);
        }
        return invocation.proceed();
    }

    /**
     * 拦截目标
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return Interceptor.super.plugin(target);
    }
}
