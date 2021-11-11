package com.li88qq.service.db;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象工具
 *
 * @author li88qq
 * @version 1.0 2021/11/3 21:49
 */
public class BeanUtil {

    /**
     * 获取实体表名
     *
     * @param aClass 实体类
     * @return 表名
     */
    public static String getTableName(Class<?> aClass) {
        String tableName = aClass.getSimpleName();
        //判断是否有Table注解,如果有,判断name值
        Table table = aClass.getAnnotation(Table.class);
        if (table != null) {
            String name = table.name();
            if (name != null) {
                tableName = name;
            }
        }
        return tableName;
    }

    /**
     * 获取id字段名
     *
     * @param aClass 实体类
     * @return id字段名
     */
    public static String getIdName(Class<?> aClass) {
        String idName = null;
        Field[] fields = aClass.getDeclaredFields();
        //判断是否有Id注解,这里不判断是否有多个
        Id id = null;
        for (Field field : fields) {
            id = field.getAnnotation(Id.class);
            if (id != null) {
                idName = field.getName();
                break;
            }
        }
        return idName;
    }

    /**
     * 获取实体字段map
     *
     * @param t   实体对象
     * @param <T> 泛型
     * @return 字段map, key为字段名, value为字段值
     */
    public static <T> Map<String, Object> getParamMap(T t) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        Class<?> aClass = t.getClass();
        //不考虑继承父类
        Field[] declaredFields = aClass.getDeclaredFields();
        //是否忽略字段
        for (Field field : declaredFields) {
            if (field.getAnnotation(Transient.class) != null) {
                continue;
            }
            //设置可访问
            field.setAccessible(true);
            paramMap.put(field.getName(), field.get(t));
        }
        return paramMap;
    }

    /**
     * 获取对象字段名称列表
     *
     * @param aClass 对象
     * @return 字段名称列表
     */
    public static List<String> getClassFields(Class<?> aClass) {
        List<String> fields = new ArrayList<>();
        //不考虑继承父类
        Field[] declaredFields = aClass.getDeclaredFields();
        //是否忽略字段
        for (Field field : declaredFields) {
            if (field.getAnnotation(Transient.class) != null) {
                continue;
            }
            fields.add(field.getName());
        }
        return fields;
    }

    /**
     * 构建insert语句
     *
     * @param ignoreRepeat 是否忽略重复
     * @param tableName    表名
     * @param fields       字段名称列表
     * @param count        实体数量
     * @return insert语句
     */
    public static String buildInsertSql(boolean ignoreRepeat, String tableName, List<String> fields, int count) {
        StringBuilder sql = new StringBuilder();

        //字段sql
        String fieldSql = String.join(",", fields);
        //问号sql
        String markSql = buildInsertMarkSql(count, fields.size());

        //insert ignore into tableName (id,name) values (?,?),(?,?);
        sql.append("insert ");
        if (ignoreRepeat) {
            sql.append("ignore ");
        }
        sql.append("into ").append(tableName);
        sql.append(" (").append(fieldSql).append(") ");
        sql.append("values ").append(markSql).append(";");

        return sql.toString();
    }

    /**
     * 构建insert问号sql
     *
     * @param count     实体数量
     * @param fieldSize 字段数量
     * @return
     */
    private static String buildInsertMarkSql(int count, int fieldSize) {
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sql.append("(");
            for (int j = 0; j < fieldSize; j++) {
                sql.append("?").append(",");
            }
            sql.deleteCharAt(sql.lastIndexOf(","));
            sql.append(")").append(",");
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        return sql.toString();
    }

    /**
     * 构建更新sql
     *
     * @param tableName 表名
     * @param fields    字段列表
     * @return 更新sql
     */
    public static String buildUpdateSql(String tableName, List<String> fields, String idName) {
        StringBuilder sql = new StringBuilder();

        //update tableName set k=?,v=? where id = ?
        sql.append("update ").append(tableName);
        sql.append(" set ");
        for (String field : fields) {
            if (field.equals(idName)) {
                continue;
            }
            sql.append(field).append(" = ").append("?").append(",");
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        sql.append(" where ").append(idName).append(" = ").append("?").append(";");

        return sql.toString();
    }

}
