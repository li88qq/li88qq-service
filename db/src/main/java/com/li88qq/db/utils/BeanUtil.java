package com.li88qq.db.utils;

import com.li88qq.db.annotion.Column;
import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;
import com.li88qq.db.annotion.Transient;
import com.li88qq.db.dto.bean.BeanDto;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * bean工具类
 *
 * @author li88qq
 * @version 1.0 2023/2/22 22:23
 */
public class BeanUtil {

    /**
     * 构建dto对象
     *
     * @param aClass 实体类
     * @return dto对象
     */
    public static BeanDto buildDto(Class<?> aClass) {
        Field[] fields = aClass.getDeclaredFields();
        Assert.isTrue(fields.length > 0, "实体对象字段为空");

        List<String> idList = new ArrayList<>();
        List<String> fieldList = new ArrayList<>();
        List<String> allList = new ArrayList<>();
        Map<String, String> columnMap = new HashMap<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Transient.class)) {
                continue;
            }
            String fieldName = field.getName();
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                String columnValue = column.value();
                if (columnValue != null && !columnValue.equals("")) {
                    if (!columnValue.equals(fieldName)) {
                        columnMap.put(columnValue, fieldName);
                    }
                    fieldName = columnValue;
                }
            }
            if (field.isAnnotationPresent(Id.class)) {
                idList.add(fieldName);
            } else {
                fieldList.add(fieldName);
            }
            allList.add(fieldName);
        }

        Assert.isTrue(allList.size() > 0, "实体对象字段为空");

        BeanDto beanDto = new BeanDto();
        beanDto.setIds(idList.toArray(new String[0]));
        beanDto.setFields(fieldList.toArray(new String[0]));
        beanDto.setAllFields(allList.toArray(new String[0]));
        beanDto.setColumnMap(columnMap);
        return beanDto;
    }

    /**
     * 获取一个实体类对应的表名
     * <p>
     * 1.取类名称
     * 2.如果指定了@Table注解且有值,取该值
     * </p>
     *
     * @param aClass 实体类
     * @return 表名
     */
    public static String buildTable(Class<?> aClass) {
        String tableName = aClass.getSimpleName();
        Table table = aClass.getDeclaredAnnotation(Table.class);
        if (table != null) {
            String value = table.value();
            if (value != null && !value.equals("")) {
                tableName = value;
            }
        }
        return tableName;
    }

    /**
     * 重置一个对象的所有字段为null
     *
     * @param aClass 实体类
     * @return 字段全为null的对象
     */
    public static <T> T reset(Class<T> aClass) {
        Constructor<T> constructor = null;
        try {
            constructor = aClass.getDeclaredConstructor();
            T instance = constructor.newInstance();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(instance, null);
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
