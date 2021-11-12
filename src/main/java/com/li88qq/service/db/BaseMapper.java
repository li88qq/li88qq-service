package com.li88qq.service.db;

import java.util.List;

/**
 * mapper
 *
 * @author li88qq
 * @version 1.0 2021/11/2 23:03
 */
public interface BaseMapper {

    /**
     * 保存一个实体,返回实体
     *
     * @param t   实体对象
     * @param <T> 泛型
     * @return 实体对象
     */
    <T> T insert(T t);

    /**
     * 保存一个实体,返回实体id
     *
     * @param t      实体对象
     * @param kClass id类型
     * @param <K>    泛型
     * @param <T>    泛型
     * @return 实体id
     */
    <K, T> K insertToId(T t, Class<K> kClass);

    /**
     * 批量保存
     *
     * @param ignoreRepeat 是否忽略重复key
     * @param tList        实体列表
     * @param <T>          泛型
     * @return 影响行数
     */
    <T> long insertArray(boolean ignoreRepeat, List<T> tList);

    /**
     * 更新一个实体,id字段必须有值
     *
     * @param t   实体对象
     * @param <T> 泛型
     * @return 影响行数
     */
    <T> int update(T t);

    /**
     * 批量更新,id字段必须有值
     *
     * @param tList 实体列表
     * @param <T>   泛型
     * @return 影响行数
     */
    <T> long updateArray(List<T> tList);

    /**
     * 根据id,如果不存在,则保存,存在则修改
     *
     * @param t   实体对象
     * @param <T> 泛型
     * @return 实体对象
     */
    <T> T saveOrUpdate(T t);

    /**
     * 更新一个实体,id字段必须有值,只更新值不为空的字段
     *
     * @param t   实体对象
     * @param <T> 泛型
     * @return 影响行数
     */
    <T> int executeUpdate(T t);
}
