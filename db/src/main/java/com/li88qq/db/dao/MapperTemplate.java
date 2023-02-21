package com.li88qq.db.dao;

import com.li88qq.db.dto.sql.SqlDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 模板
 *
 * @author li88qq
 * @version 1.0 2023/2/21 21:40
 */
public interface MapperTemplate {

    /**
     * 新增
     *
     * @param dto sql
     * @param t   实体
     * @return 影响行数
     */
    @Insert("insert into ${dto.table} (${dto.keys}) values (${dto.values})")
    <T> int insertNoId(@Param("dto") SqlDto dto, @Param("t") T t);

    /**
     * 新增,返回自增id
     *
     * @param dto sql
     * @param t   实体
     * @return 影响行数
     */
    @Insert("insert into ${dto.table} (${dto.keys}) values (${dto.values})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    <T> int insertId(@Param("dto") SqlDto dto, @Param("t") T t);

    /**
     * 批量新增
     *
     * @param dto  sql
     * @param list 实体列表
     * @return 影响行数
     */
    @Insert("<script>" +
            "insert ${dto.ignore} into ${dto.table} (${dto.keys}) values " +
            "<foreach item=\"t\" collection=\"list\" separator=\",\">" +
            "(${dto.values})" +
            "</foreach>" +
            "</script>")
    <T> int insertList(@Param("dto") SqlDto dto, List<T> list);

    /**
     * 修改
     *
     * @param dto sql
     * @param t   实体
     * @return 影响行数
     */
    @Update("update ${dto.table} set ${dto.set} where ${dto.where}")
    <T> int update(@Param("dto") SqlDto dto, @Param("t") T t);

    /**
     * 删除
     *
     * @param dto sql
     * @param t   实体
     * @return 影响行数
     */
    @Delete("delete from ${dto.table} where ${dto.where}")
    <T> int delete(@Param("dto") SqlDto dto, @Param("t") T t);
}
