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
        return buildDto(aClass, null);
    }

    /**
     * 构建dto对象
     *
     * @param aClass 实体类
     * @param t      实体类,过滤非空字段
     * @return dto对象
     */
    public static <T> BeanDto buildDto(Class<?> aClass, T t) {
        Field[] fields = aClass.getDeclaredFields();
        Assert.isTrue(fields.length > 0, "实体对象字段为空");

        List<String> idList = new ArrayList<>();
        List<String> fieldList = new ArrayList<>();
        List<String> allList = new ArrayList<>();
        Map<String, String> columnMap = new HashMap<>();
        try {
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

                //判断是否需要过滤
                boolean id = field.isAnnotationPresent(Id.class);
                if (t != null) {
                    field.setAccessible(true);
                    Object fieldValue = field.get(t);
                    if (fieldValue == null) {
                        if (id) {
                            throw new RuntimeException("@Id字段不能为空");
                        }
                        continue;
                    }
                }

                if (id) {
                    idList.add(fieldName);
                } else {
                    fieldList.add(fieldName);
                }
                allList.add(fieldName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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
                if (!field.canAccess(instance)) {
                    field.setAccessible(true);
                }
                field.set(instance, null);
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * map转实体
     *
     * @param map    字段map
     * @param aClass 实体类
     * @return 实体
     */
    public static <T> T fromMap(Map<String, Object> map, Class<T> aClass) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        BeanDto beanDto = buildDto(aClass);
        String[] allFields = beanDto.getAllFields();
        Map<String, String> columnMap = beanDto.getColumnMap();//列对应字段
        String[] columns = SqlDtoBuilder.convertColumns(allFields, columnMap);
        T t = null;
        try {
            t = aClass.getDeclaredConstructor().newInstance();
            for (int i = 0; i < allFields.length; i++) {
                String field = columns[i];
                String column = allFields[i];
                Field declaredField = aClass.getDeclaredField(field);
                declaredField.setAccessible(true);
                declaredField.set(t, map.get(column));
            }
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
