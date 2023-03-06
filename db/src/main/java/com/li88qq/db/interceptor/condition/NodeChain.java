package com.li88qq.db.interceptor.condition;

/**
 * 职责链
 *
 * @author li88qq
 * @version 1.0 2023/3/5 20:19
 */
public interface NodeChain {

    /**
     * 是否处理
     *
     * @param aClass 值类型
     * @return 是否处理
     */
    boolean check(Class<?> aClass);

    /**
     * 格式化
     */
    default void format() {
    }

    /**
     * 构建SqlNode
     */
    default void build() {
    }

}
