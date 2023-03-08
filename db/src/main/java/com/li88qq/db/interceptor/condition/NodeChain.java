package com.li88qq.db.interceptor.condition;

/**
 * 动态条件职责链
 *
 * @author li88qq
 * @version 1.0 2023/3/8 23:41
 */
public interface NodeChain {

    /**
     * 是否处理
     *
     * @param aClass 值类型
     * @return 是否处理
     */
    boolean check(Class<?> aClass);

}
