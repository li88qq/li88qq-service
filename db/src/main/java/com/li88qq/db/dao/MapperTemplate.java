package com.li88qq.db.dao;

import com.li88qq.db.annotion.InsertId;
import com.li88qq.db.dto.sql.SqlDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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
    @Options(useGeneratedKeys = true, keyProperty = "t.id", keyColumn = "id")
    @InsertId
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
    <T> int insertList(@Param("dto") SqlDto dto, @Param("list") List<T> list);

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

    /**
     * 根据唯一主键查询
     *
     * @param dto sql
     * @param id  id值
     * @return 实体
     */
    @Select("select * from ${dto.table} where ${dto.where}")
    <K> Map<String, Object> find(@Param("dto") SqlDto dto, @Param("id") K id);

    /**
     * 分页统计数量
     *
     * @param sql      sql
     * @param paramMap 参数
     * @return 总数
     */
    @Select("${sql}")
    long queryCount(@Param("sql") String sql, @Param("map") Map<String, Object> paramMap);

    /**
     * 批量更新
     *
     * @param dto  SqlDto
     * @param list 列表
     * @return 影响行数
     */
    @Update("<script>" +
            "update ${dto.table} set" +
            "<foreach item=\"field\" collection=\"dto.setFields\" separator=\",\" index=\"index\">" +
            "${field} = case ${dto.idField} " +
            "<foreach item=\"t\" collection=\"list\" separator=\"\" open=\"\" close=\"end\">" +
            "when #{t.${dto.idField}} then #{t.${dto.setColumns[index]}}" +
            "</foreach>" +
            "</foreach>" +
            "where ${dto.idField} in " +
            "<foreach item=\"t\" collection=\"list\" separator=\",\" open=\"(\" close=\")\">" +
            "#{t.${dto.idField}}" +
            "</foreach>" +
            "</script>")
    <T> int updateList(@Param("dto") SqlDto dto, @Param("list") List<T> list);

    /**
     * 分页统计数量-groupBy
     *
     * @param sql      sql
     * @param paramMap 参数
     * @return 总数
     */
    @Select("${sql}")
    List<Map<String,Object>> queryCountGroupBy(@Param("sql") String sql, @Param("map") Map<String, Object> paramMap);
}
