package com.li88qq.db.service;

import java.util.List;

/**
 * mapper接口
 *
 * @author li88qq
 * @version 1.0 2023/2/21 21:41
 */
public interface Mapper {

    /**
     * 新增
     *
     * @param t 实体
     * @return 影响行数
     */
    <T> int insertNoId(T t);

    /**
     * 新增
     *
     * @param t       实体
     * @param idClass id类型
     * @return 自增id
     */
    <T, K> K insertId(T t, Class<K> idClass);

    /**
     * 修改
     *
     * @param t 实体
     * @return 影响行数
     */
    <T> int update(T t);

    /**
     * 修改
     *
     * @param t 实体,只修改非空字段
     * @return 影响行数
     */
    <T> int updateNotNull(T t);

    /**
     * 删除
     *
     * @param t 实体
     * @return 影响行数
     */
    <T> int delete(T t);

    /**
     * 新增(批量)
     *
     * @param list         列表
     * @param ignoreRepeat 是否忽略主键重复
     * @return 影响行数
     */
    <T> int insertList(List<T> list, boolean ignoreRepeat);

    /**
     * 根据id查询
     *
     * @param aClass 实体类
     * @param id     id值
     * @return 根据id查询
     */
    <T, K> T find(Class<T> aClass, K id);

    /**
     * 批量修改
     *
     * @param list 列表
     * @return 影响行数
     */
    <T> int updateList(List<T> list);

}
