package com.li88qq.db.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

/**
 * StatementInterceptor
 *
 * @author li88qq
 * @version 1.0 2023/2/28 22:14
 */
@Component
public class StatementInterceptor {

    public Object invoke(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        //找到对应方法
        //如果没参数,跳过
        //切换数据库
        //分页参数处理
        //InsertId
        //PageId
        //动态参数处理

        return invocation.proceed();
    }
}
