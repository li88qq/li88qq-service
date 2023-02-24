package com.li88qq.db.utils;

import com.li88qq.db.dto.bean.BeanDto;
import com.li88qq.db.dto.sql.SqlDto;

import java.util.Map;

/**
 * sqlDto builder
 *
 * @author li88qq
 * @version 1.0 2023/2/21 23:09
 */
public class SqlDtoBuilder {

    //MapperTemplate中SqlDto对应参数
    public static final String PARAM_DTO = "dto";
    //分隔符号
    private static final String SEP = ",";
    private static final String VALUE_FORMAT = "#{%s.%s}";

    /**
     * 构建insert对象
     *
     * @param aClass       实体类
     * @param ignoreRepeat 是否忽略重复
     * @return sqlDto
     */
    public static <T> SqlDto buildInsert(Class<T> aClass, boolean ignoreRepeat) {
        BeanDto beanDto = BeanUtil.buildDto(aClass);
        String[] fields = beanDto.getAllFields();

        String[] columns = convertColumns(fields, beanDto.getColumnMap());
        String[] valueColumns = convertValue(columns);
        String keys = String.join(SEP, fields);
        String values = String.join(SEP, valueColumns);

        String table = BeanUtil.buildTable(aClass);
        String ignore = ignoreRepeat ? "ignore" : "";

        SqlDto sqlDto = new SqlDto();
        sqlDto.setTable(table);
        sqlDto.setIgnore(ignore);
        sqlDto.setKeys(keys);
        sqlDto.setValues(values);
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
            result[i] = String.format(VALUE_FORMAT, PARAM_DTO, values[i]);
        }
        return result;
    }

}
