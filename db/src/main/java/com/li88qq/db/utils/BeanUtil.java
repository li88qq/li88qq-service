package com.li88qq.db.utils;

import com.li88qq.db.annotation.Id;
import com.li88qq.db.annotation.Table;
import com.li88qq.db.dto.BeanDto;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对象工具类
 *
 * @author li88qq
 * @version 1.0 2021/12/10 23:04
 */
public class BeanUtil {

    /**
     * 获取类属性
     *
     * @param aClass 类
     * @return 类属性
     */
    public static BeanDto buildBeanDto(Class<?> aClass) {
        String tableName = getTableName(aClass);
        List<String> idNames = new ArrayList<>();
        List<String> fields = new ArrayList<>();

        String fieldName = null;
        for (Field field : aClass.getDeclaredFields()) {
            fieldName = field.getName();
            if (field.isAnnotationPresent(Id.class)) {
                idNames.add(fieldName);
            }
            fields.add(fieldName);
        }

        BeanDto beanDto = new BeanDto();
        beanDto.setTableName(tableName);
        beanDto.setIdNames(idNames.toArray(new String[0]));
        beanDto.setFields(fields.toArray(new String[0]));
        return beanDto;
    }

    /**
     * 获取实体类对应表名
     *
     * @param aClass 实体类
     * @return 表名
     */
    private static String getTableName(Class<?> aClass) {
        String tableName = aClass.getSimpleName();
        Table annotation = aClass.getAnnotation(Table.class);
        if (annotation != null) {
            String name = annotation.name();
            if (name != null && !name.equals("")) {
                tableName = name;
            }
        }
        return tableName;
    }

    /**
     * 把一个对象所有属性重置为null
     *
     * @param aClass 实体类
     * @return 一个属性都为空的对象
     */
    public static <T> T reset(Class<T> aClass) {
        try {
            Constructor<T> constructor = aClass.getConstructor();
            T t = constructor.newInstance();
            for (Field field : aClass.getDeclaredFields()) {
                if (!field.canAccess(t)) {
                    field.setAccessible(true);
                }
                field.set(t, null);
            }
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * map转换对象
     *
     * @param map    map
     * @param tClass 转换类型
     * @param <T>    泛型
     * @return map转对象
     */
    public static <T> T toBean(Map<String, Object> map, Class<T> tClass) {
        try {
            Field[] declaredFields = tClass.getDeclaredFields();
            T t = tClass.getConstructor().newInstance();
            for (Field field : declaredFields) {
                if (!field.canAccess(t)) {
                    field.setAccessible(true);
                }
                field.set(t, map.get(field.getName()));
            }
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对象转换(列表)
     *
     * @param list   list
     * @param tClass 转换类型
     * @param <T>    泛型
     * @return 列表
     */
    public static <T> List<T> toBean(List<Map<String, Object>> list, Class<T> tClass) {
        List<T> result = new ArrayList<>();
        list.forEach(map -> {
            result.add(toBean(map, tClass));
        });
        return result;
    }
}
