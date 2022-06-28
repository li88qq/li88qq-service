package com.li88qq.db.core;

import org.apache.ibatis.annotations.*;

/**
 * mapper工厂
 *
 * @author li88qq
 * @version 1.0 2022/6/20 23:35
 */
public interface MapperFactory {

    /**
     * 保存实体
     *
     * @param t   实体
     * @param sql insert语句
     * @return 影响行数
     */
    @Insert("${sql}")
    <T> int save(@Param("t") T t, @Param("sql") String sql);

    /**
     * 保存实体并返回自增主键
     *
     * @param t   实体对象
     * @param sql insert语句
     * @param id  自增主键
     * @return 影响行数, 返回的实体对象中会自动赋值主键
     */
    @Insert("${sql}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    <T> int saveToId(@Param("t") T t, @Param("sql") String sql, @Param("id") String id);

    /**
     * 保存实体
     *
     * @param t   实体
     * @param sql update语句
     * @return 影响行数
     */
    @Update("${sql}")
    <T> int update(@Param("t") T t, @Param("sql") String sql);

    /**
     * 根据主键删除实体
     *
     * @param t   实体
     * @param sql delete语句
     * @return 影响行数
     */
    @Delete("${sql}")
    <T> int delete(@Param("t") T t, @Param("sql") String sql);
}
