package com.li88qq.db2.core;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * mapper实现
 *
 * @author li88qq
 * @version 1.0 2022/3/6 23:28
 */
@Component
public class SimpleMapper implements Mapper {

    @Resource
    private SimpleQuery simpleQuery;

    /**
     * 保存
     */
    @Override
    public <T> int save(T t) {
        return simpleQuery.query(QueryType.INSERT, t);
    }

    /**
     * 保存,返回id
     */
    @Override
    public <T, K extends Number> K saveId(T t, Class<K> kClass) {
        return simpleQuery.queryId(QueryType.INSERT_ID, t, kClass);
    }

    /**
     * 修改
     */
    @Override
    public <T> int update(T t) {
        return simpleQuery.query(QueryType.UPDATE, t);
    }

    /**
     * 修改,只修改非空字段
     */
    @Override
    public <T> int updateNoNull(T t) {
        return simpleQuery.query(QueryType.UPDATE_NOT_NULL, t);
    }

    /**
     * 删除
     */
    @Override
    public <T> int delete(T t) {
        return simpleQuery.query(QueryType.DELETE, t);
    }
}
