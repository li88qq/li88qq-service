package com.li88qq.db2.core;

/**
 * mapper
 *
 * @author li88qq
 * @version 1.0 2022/3/6 23:29
 */
public interface Mapper {

    /**
     * 保存实体
     *
     * @param t 实体
     */
    <T> int save(T t);

    /**
     * 保存实体,返回id
     *
     * @param t      实体
     * @param kClass id类型
     * @return id
     */
    <T, K extends Number> K saveId(T t, Class<K> kClass);

    /**
     * 修改实体,返回影响行数
     *
     * @param t 实体
     * @return 影响行数
     */
    <T> int update(T t);

    /**
     * 修改实体,返回影响行数,只修改非null字段
     *
     * @param t 实体
     * @return 影响行数
     */
    <T> int updateNoNull(T t);

    /**
     * 删除实体,返回影响行数
     *
     * @param t 实体
     * @return 影响行数
     */
    <T> int delete(T t);

}
