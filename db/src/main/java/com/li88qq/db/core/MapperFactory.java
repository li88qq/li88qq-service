package com.li88qq.db.core;

import org.apache.ibatis.annotations.*;

import java.util.List;

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
     * @param sqlDto sql封装对象
     * @param t      实体对象
     * @return 影响行数
     */
    @NativeQuery
    @Insert("insert into ${dto.tableName} (${dto.fields}) values (${dto.values})")
    <T> int insert(@Param("dto") SqlDto sqlDto, @Param("t") T t);

    /**
     * 保存实体并返回自增主键
     *
     * @param sqlDto sql封装对象
     * @param t      实体对象
     * @return 影响行数, 返回的实体对象中会自动赋值主键
     */
    @NativeQuery
    @Insert("insert into ${dto.tableName} (${dto.fields}) values (${dto.values})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    <T> int insertId(@Param("dto") SqlDto sqlDto, @Param("t") T t);

    /**
     * 批量保存
     *
     * @param sqlDto sql封装对象
     * @param list   实体列表
     * @return 影响行数
     */
    @NativeQuery
    @Insert("<script>" + "insert into ${dto.tableName} (${dto.fields}) values " + "<foreach item=\"t\" collection=\"list\" separator=\",\">" +
            "(${dto.values})" +
            "</foreach></script>")
    <T> int insertList(@Param("dto") SqlDto sqlDto, @Param("list") List<T> list);

    /**
     * 批量保存,忽略重复主键
     *
     * @param sqlDto sql封装对象
     * @param list   实体列表
     * @return 影响行数
     */
    @NativeQuery
    @Insert("<script>" + "insert ignore into ${dto.tableName} (${dto.fields}) values " + "<foreach item=\"t\" collection=\"list\" separator=\",\">" +
            "(${dto.values})" +
            "</foreach></script>")
    <T> int insertListIgnore(@Param("dto") SqlDto sqlDto, @Param("list") List<T> list);

    /**
     * 根据主键更新
     *
     * @param sqlDto sql封装对象
     * @param sql    sql
     * @param t      实体对象
     * @return 影响行数
     */
    //update table set name = #{t.name} where id = #{id]
    @NativeQuery
    @Update("update ${dto.tableName} ${sql}")
    <T> int update(@Param("dto") SqlDto sqlDto, @Param("sql") String sql, @Param("t") T t);

    /**
     * 根据主键删除
     *
     * @param sqlDto sql封装对象
     * @param sql    sql
     * @param t      实体对象
     * @return 影响行数
     */
    //delete from table where id = #{t.id}
    @NativeQuery
    @Delete("delete from ${dto.tableName} ${sql}")
    <T> int delete(@Param("dto") SqlDto sqlDto, @Param("sql") String sql, @Param("t") T t);

}
