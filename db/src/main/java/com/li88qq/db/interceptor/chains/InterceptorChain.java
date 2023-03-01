package com.li88qq.db.interceptor.chains;

import org.apache.ibatis.executor.statement.StatementHandler;

import java.lang.reflect.Method;

/**
 * 拦截链
 *
 * @author li88qq
 * @version 1.0 2023/3/1 21:34
 */
public interface InterceptorChain {

    /**
     * 执行
     *
     * @param handler handler
     * @param method  方法
     * @return 是否执行下一个拦截, 如果true, 继续下一个, false, 则退出
     */
    boolean execute(StatementHandler handler, Method method);
}
