package com.li88qq.db.interceptor;

import com.li88qq.db.dto.MethodDto;
import com.li88qq.db.dto.Pageable;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * mybatis拦截器
 *
 * @author li88qq
 * @version 1.0 2021/12/21 22:27
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 通过MetaObject访问对象的属性
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        // 获取成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //分析调用方法
        MethodDto methodDto = analyse(statementHandler.getBoundSql(), mappedStatement.getId());

        //处理分页
        chain_page(metaObject, statementHandler.getBoundSql(), methodDto);

        return invocation.proceed();
    }

    /**
     * 应用插件
     *
     * @param target 调用对象
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 调用方法分析
     *
     * @param boundSql    boundSql
     * @param statementId statementId
     * @return 调用方法
     */
    @SuppressWarnings("unchecked")
    private MethodDto analyse(BoundSql boundSql, String statementId) throws Throwable {
        Class<?> aClass = null;
        Method method = null;
        Pageable pageable = null;

        //statementId构成:类名.方法名,且方法名在类中唯一,不管参数或返回类型是否一致
        int classIndex = statementId.lastIndexOf(".");
        String className = statementId.substring(0, classIndex);
        String methodName = statementId.substring(classIndex + 1);

        aClass = Class.forName(className);
        for (Method method1 : aClass.getDeclaredMethods()) {
            if (method1.getName().equals(methodName)) {
                method = method1;
                break;
            }
        }

        //判断是否有分页参数
        Parameter[] parameters = method.getParameters();
        String pageableParam = null;

        if (parameters != null && parameters.length > 0) {
            for (Parameter parameter : parameters) {
                if (parameter.getType().equals(Pageable.class)) {
                    pageableParam = parameter.getName();
                    break;
                }
            }
            Object parameterObject = boundSql.getParameterObject();
            if (pageableParam != null) {
                if (parameters.length == 1) { //单个对象
                    pageable = (Pageable) parameterObject;
                } else { //多个参数map
                    Map<String, Object> paramMap = (HashMap<String, Object>) parameterObject;
                    pageable = (Pageable) paramMap.get(pageableParam);
                }
            }
        }

        MethodDto methodDto = new MethodDto();
        methodDto.setClazz(aClass);
        methodDto.setMethod(method);
        methodDto.setPageable(pageable);
        return methodDto;
    }

    /**
     * 拦截链-处理分页
     *
     * @param metaObject metaObject
     * @param boundSql   boundSql
     * @param methodDto  方法对象
     */
    private void chain_page(MetaObject metaObject, BoundSql boundSql, MethodDto methodDto) {
        Pageable pageable = methodDto.getPageable();
        if (pageable == null) {
            return;
        }
        String sql = String.join("", boundSql.getSql(), mysqlDialect(pageable));
        //替换sql
        metaObject.setValue("delegate.boundSql.sql", sql);
    }

    /**
     * mysql分页方言
     *
     * @param pageable pageable
     * @return mysql分页语句
     */
    private String mysqlDialect(Pageable pageable) {
        int page = pageable.getPage();
        int pageSize = pageable.getPageSize();

        if (page <= 0) {
            page = 1;
            pageable.setPage(page);
        }
        if (pageSize <= 0) {
            pageSize = 15;
            pageable.setPageSize(pageSize);
        }
        int start = (page - 1) * pageSize;

        StringBuilder sql = new StringBuilder();
        sql.append(" limit ").append(start).append(",").append(pageSize);
        return sql.toString();
    }
}
