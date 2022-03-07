package com.li88qq.db2.core;

import java.util.List;

/**
 * sql构建工厂
 *
 * @author li88qq
 * @version 1.0 2022/3/7 23:16
 */
public class SqlFactory {

    /**
     * 构建sql元数据
     *
     * @param queryType 操作类型
     * @param t         实体对象
     * @return sql元数据
     */
    public static <T> SqlMeta buildSql(QueryType queryType, T t) {
        return null;
    }

    /**
     * 构建sql元数据
     *
     * @param queryType 操作类型
     * @param list      实体列表
     * @return sql元数据
     */
    public static <T> SqlMeta buildBatchSql(QueryType queryType, List<T> list) {
        return null;
    }

    /**
     * 构建class元数据
     *
     * @param aClass 实体类
     * @return class元数据
     */
    public static ClassMeta getClassMeta(Class<?> aClass) {
        return null;
    }
}
