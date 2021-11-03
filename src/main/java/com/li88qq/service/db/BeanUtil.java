package com.li88qq.service.db;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.util.HashMap;
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

}
