package com.li88qq.db.utils;

import com.li88qq.db.annotations.Id;
import com.li88qq.db.annotations.Table;
import com.li88qq.db.dto.BeanDto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * bean工具
 *
 * @author li88qq
 * @version 1.0 2021/12/30 23:40
 */
public class BeanUtil {

    /**
     * map转换对象
     *
     * @param map    map数据
     * @param tClass 转换类型
     * @param <T>    泛型
     * @return 实体对象
     */
    public static <T> T toBean(Map<String, Object> map, Class<T> tClass) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        try {
            T t = tClass.getConstructor().newInstance();
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
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
     * 转换列表
     *
     * @param maps   map数据
     * @param tClass 转换类型
     * @param <T>    泛型
     * @return 列表, 不返回null
     */
    public static <T> List<T> toList(List<Map<String, Object>> maps, Class<T> tClass) {
        if (maps == null || maps.isEmpty()) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        maps.forEach(map -> {
            T t = toBean(map, tClass);
            if (t != null) {
                list.add(t);
            }
        });
        return list;
    }

    /**
     * 对象分析
     *
     * @param tClass 对象类型
     * @return beanDto
     */
    public static BeanDto analysis(Class<?> tClass) {
        String tableName = "";
        String[] idNames = null;
        String[] fieldNames = null;

        Table table = tClass.getAnnotation(Table.class);
        if (table != null) {
            tableName = table.value();
        }
        if (tableName == null || tableName.equals("")) {
            tableName = tClass.getSimpleName();
        }

        Field[] declaredFields = tClass.getDeclaredFields();
        fieldNames = new String[declaredFields.length];
        List<String> idList = new ArrayList<>();
        String fieldName = null;
        int index = 0;
        for (Field field : declaredFields) {
            fieldName = field.getName();
            if (field.isAnnotationPresent(Id.class)) {
                idList.add(fieldName);
            }
            fieldNames[index++] = fieldName;
        }
        idNames = idList.toArray(new String[0]);

        BeanDto beanDto = new BeanDto();
        beanDto.setTableName(tableName);
        beanDto.setIdNames(idNames);
        beanDto.setFieldNames(fieldNames);
        return beanDto;
    }
}
