package com.li88qq.db2.core;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Statement;

/**
 * 动态条件拦截器
 *
 * <p>
 * 拦截条件语句,分页处理
 * 1.拦截有 @Condition注解的条件语句,动态拼接
 * 2.拦截有Pageable参数及Page返回参数的接口,动态处理分页参数,及返回分页数据处理
 * </p>
 *
 * @author li88qq
 * @version 1.0 2022/3/11 22:25
 */
@Component
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class DynamicInterceptor implements Interceptor {

    //拦截处理
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (target instanceof StatementHandler) {
            //动态拼接条件
            return ConditionInterceptor.invoke(invocation);
        } else if (target instanceof ResultSetHandler) {
            //处理分页对象返回
            return PageInterceptor.invoke(invocation);
        }
        return invocation.proceed();
    }

    //配置插件
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
