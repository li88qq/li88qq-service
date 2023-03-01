package com.li88qq.db.interceptor.chains;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * 职责链控制器
 *
 * @author li88qq
 * @version 1.0 2023/3/1 21:35
 */
public class ChainManager {

    private final StatementHandler statementHandler;//处理器
    private final Method method;//接口方法
    private final LinkedList<InterceptorChain> chains;//拦截链

    public ChainManager(StatementHandler statementHandler) {
        this.statementHandler = statementHandler;
        this.method = this.initMethod();
        this.chains = new LinkedList<>();
    }

    /**
     * 获取方法
     *
     * @return Method
     */
    private Method initMethod() {
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String id = mappedStatement.getId();
        int index = id.lastIndexOf(".");
        String className = id.substring(0, index);
        String methodName = id.substring(index + 1);
        Class<?> aClass = null;
        try {
            aClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    /**
     * 添加拦截链
     *
     * @param chain 链
     */
    public void add(InterceptorChain chain) {
        this.chains.add(chain);
    }

    /**
     * 执行
     */
    public void execute() {
        for (InterceptorChain chain : chains) {
            boolean execute = chain.execute(statementHandler, method);
            if (!execute) {
                break;
            }
        }
    }

    public Method getMethod() {
        return method;
    }

    public LinkedList<InterceptorChain> getChains() {
        return chains;
    }

}
