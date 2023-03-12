package com.li88qq.db.utils;

import com.li88qq.db.dto.SqlConst;
import com.li88qq.db.dto.bean.BeanDto;
import com.li88qq.db.dto.sql.SqlDto;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * sqlDto builder
 *
 * @author li88qq
 * @version 1.0 2023/2/21 23:09
 */
public class SqlDtoBuilder {

    /**
     * 构建insert对象
     *
     * @param aClass       实体类
     * @param ignoreRepeat 是否忽略重复
     * @return sqlDto
     */
    public static SqlDto buildInsert(Class<?> aClass, boolean ignoreRepeat) {
        BeanDto beanDto = BeanUtil.buildDto(aClass);
        String[] fields = beanDto.getAllFields();

        String[] columns = convertColumns(fields, beanDto.getColumnMap());
        String[] valueColumns = convertValue(columns);
        String keys = String.join(SqlConst.SEP, fields);
        String values = String.join(SqlConst.SEP, valueColumns);

        String table = BeanUtil.buildTable(aClass);
        String ignore = ignoreRepeat ? SqlConst.IGNORE : "";

        String idField = null;
        String idColumn = null;
        String[] ids = beanDto.getIds();
        String[] idColumns = convertColumns(ids, beanDto.getColumnMap());
        if (ids != null && ids.length == 1) {
            idField = idColumns[0];
            idColumn = ids[0];
        }

        SqlDto sqlDto = new SqlDto();
        sqlDto.setTable(table);
        sqlDto.setIgnore(ignore);
        sqlDto.setKeys(keys);
        sqlDto.setValues(values);
        sqlDto.setIdField(idField);
        sqlDto.setIdColumn(idColumn);
        return sqlDto;
    }

    /**
     * 列转换
     *
     * @param fields    字段列表
     * @param columnMap 列转换
     * @return 转换后的字段
     */
    public static String[] convertColumns(String[] fields, Map<String, String> columnMap) {
        if (columnMap == null || columnMap.isEmpty()) {
            return fields;
        }
        String[] columns = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            String column = columnMap.get(field);
            if (column == null) {
                columns[i] = field;
            } else {
                columns[i] = column;
            }
        }
        return columns;
    }

    /**
     * 格式化值
     *
     * @param values 值列表
     * @return 格式化后的值列表
     */
    public static String[] convertValue(String[] values) {
        String[] result = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = String.format(SqlConst.VALUE_FORMAT, SqlConst.PARAM_T, values[i]);
        }
        return result;
    }

    /**
     * 构建update SqlDto
     *
     * @param aClass 实体类
     * @return SqlDto
     */
    public static SqlDto buildUpdate(Class<?> aClass) {
        BeanDto beanDto = BeanUtil.buildDto(aClass);
        String[] ids = beanDto.getIds();
        String[] fields = beanDto.getFields();

        Assert.isTrue(ids != null && ids.length > 0, "@Id注解为空");
        Assert.isTrue(fields != null && fields.length > 0, "修改字段为空");

        //数据库列对应字段
        Map<String, String> columnMap = beanDto.getColumnMap();
        String[] idColumns = convertColumns(ids, columnMap);
        String[] fieldColumns = convertColumns(fields, columnMap);

        //#{dto.字段}
        String[] fieldValue = convertValue(fieldColumns);
        String[] idValue = convertValue(idColumns);

        //列=字段
        String set = convertKv(fields, fieldValue);
        String where = convertKv(ids, idValue);

        String table = BeanUtil.buildTable(aClass);
        SqlDto sqlDto = new SqlDto();
        sqlDto.setTable(table);
        sqlDto.setSet(set);
        sqlDto.setWhere(where);
        return sqlDto;
    }

    /**
     * 字段包装为 k=v
     *
     * @param keys   key列表
     * @param values 值列表
     */
    public static String convertKv(String[] keys, String[] values) {
        Assert.isTrue(keys.length == values.length, "键值数据错误");

        String[] kv = new String[keys.length];

        for (int i = 0; i < keys.length; i++) {
            kv[i] = String.format(SqlConst.KV_FORMAT, keys[i], values[i]);
        }
        return String.join(SqlConst.SEP, kv);
    }

    /**
     * 构建delete SqlDto
     *
     * @param aClass 实体类
     * @return SqlDto
     */
    public static SqlDto buildDelete(Class<?> aClass) {
        BeanDto beanDto = BeanUtil.buildDto(aClass);
        String[] ids = beanDto.getIds();

        Assert.isTrue(ids != null && ids.length > 0, "@Id注解为空");

        Map<String, String> columnMap = beanDto.getColumnMap();
        String[] idColumns = convertColumns(ids, columnMap);

        String[] idValue = convertValue(ids);

        String where = convertKv(idColumns, idValue);

        String table = BeanUtil.buildTable(aClass);
        SqlDto sqlDto = new SqlDto();
        sqlDto.setTable(table);
        sqlDto.setWhere(where);
        return sqlDto;
    }

    /**
     * 构建find SqlDto
     *
     * @param aClass 实体类
     * @return SqlDto
     */
    public static SqlDto buildFind(Class<?> aClass) {
        BeanDto beanDto = BeanUtil.buildDto(aClass);
        String[] ids = beanDto.getIds();

        Assert.isTrue(ids != null && ids.length == 1, "该方法仅支持一个且仅一个@Id注解字段");
        String[] idValue = new String[]{"#{id}"};

        //where id_column = #{id}
        String where = convertKv(ids, idValue);
        String table = BeanUtil.buildTable(aClass);
        SqlDto sqlDto = new SqlDto();
        sqlDto.setTable(table);
        sqlDto.setWhere(where);
        return sqlDto;
    }

    /**
     * 构建updateNoNull SqlDto
     *
     * @param aClass 实体类
     * @param t      实体
     * @return SqlDto
     */
    public static <T> SqlDto buildUpdateNotNull(Class<?> aClass, T t) {
        Assert.notNull(t,"实体对象不能为空");
        BeanDto beanDto = BeanUtil.buildDto(aClass,t);
        String[] ids = beanDto.getIds();
        String[] fields = beanDto.getFields();

        Assert.isTrue(ids != null && ids.length > 0, "@Id注解为空");
        Assert.isTrue(fields != null && fields.length > 0, "修改字段为空");

        //数据库列对应字段
        Map<String, String> columnMap = beanDto.getColumnMap();
        String[] idColumns = convertColumns(ids, columnMap);
        String[] fieldColumns = convertColumns(fields, columnMap);

        //#{dto.字段}
        String[] fieldValue = convertValue(fieldColumns);
        String[] idValue = convertValue(idColumns);

        //列=字段
        String set = convertKv(fields, fieldValue);
        String where = convertKv(ids, idValue);

        String table = BeanUtil.buildTable(aClass);
        SqlDto sqlDto = new SqlDto();
        sqlDto.setTable(table);
        sqlDto.setSet(set);
        sqlDto.setWhere(where);
        return sqlDto;
    }

    /**
     * 构建updateList SqlDto
     *
     * @param list 列表
     * @return SqlDto
     */
    public static SqlDto buildUpdateList(List<?> list) {
        Class<?> aClass = list.get(0).getClass();
        BeanDto beanDto = BeanUtil.buildDto(aClass);
        String[] ids = beanDto.getIds();

        Assert.isTrue(ids != null && ids.length == 1, "该方法仅支持一个且仅一个@Id注解字段");
        Map<String, String> columnMap = beanDto.getColumnMap();
        String[] idColumns = convertColumns(ids, columnMap);

        String idField = idColumns[0];
        String idColumn = ids[0];

        String[] setFields = beanDto.getFields();
        String[] setColumns = convertColumns(setFields, columnMap);

        String table = BeanUtil.buildTable(aClass);

        SqlDto sqlDto = new SqlDto();
        sqlDto.setTable(table);
        sqlDto.setIdField(idField);
        sqlDto.setIdColumn(idColumn);
        sqlDto.setSetFields(setFields);
        sqlDto.setSetColumns(setColumns);
        return sqlDto;
    }

}
