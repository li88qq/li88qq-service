package com.li88qq.db2.core;

/**
 * mapper实现
 *
 * @author li88qq
 * @version 1.0 2022/3/6 23:28
 */
public class SimpleMapper implements Mapper {

    @Override
    public <T> void save(T t) {

    }

    @Override
    public <T, K extends Number> K saveId(T t, Class<K> idClass) {
        return null;
    }

    @Override
    public <T> int update(T t) {
        return 0;
    }

    @Override
    public <T> int updateNoNull(T t) {
        return 0;
    }

    @Override
    public <T> int delete(T t) {
        return 0;
    }
}
