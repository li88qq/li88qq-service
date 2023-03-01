package com.li88qq.db.interceptor.chains;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 方法拦截
 *
 * @author li88qq
 * @version 1.0 2023/3/1 22:30
 */
@Component
public class MethodChain implements InterceptorChain {

    @Override
    public boolean execute(StatementHandler handler, Method method) {
        //方法不存在,不处理
        if (method == null) {
            return false;
        }
        //没参数,不处理
        int parameterCount = method.getParameterCount();
        if (parameterCount == 0) {
            return false;
        }
        return true;
    }
}
