package com.li88qq.db.core;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * mapper实现
 *
 * @author li88qq
 * @version 1.0 2022/3/6 23:28
 */
@Component
public class BaseMapper implements Mapper {

    @Resource
    private MapperFactory mapperFactory;

    /**
     * 保存
     */
    @Override
    public <T> int save(T t) {
        SqlDto sqlDto = buildSqlDto(t.getClass());
        int effect = mapperFactory.insert(sqlDto, t);
        return effect;
    }

    /**
     * 保存,返回id
     */
    @Override
    public <T, K extends Number> K saveId(T t, Class<K> kClass) {
        try {
            SqlDto sqlDto = buildSqlDto(t.getClass());
            int effect = mapperFactory.insertId(sqlDto, t);
            String idName = sqlDto.getId();
            if (idName == null || idName.equals("")) {
                throw new RuntimeException("@Id注解为空!");
            }
            String[] ids = idName.split(",");
            if (ids.length != 1) {
                throw new RuntimeException("该方法仅支持一个@Id注解!");
            }
            Field idField = t.getClass().getDeclaredField(ids[0]);
            Object id = idField.get(t);
            if (id.getClass() == kClass) {
                return (K) id;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改
     */
    @Override
    public <T> int update(T t) {
        SqlDto sqlDto = buildSqlDto(t.getClass());
        String sql = buildUpdateSql(sqlDto, false);
        int effect = mapperFactory.update(sqlDto, sql, t);
        return effect;
    }

    /**
     * 修改,只修改非空字段
     */
    @Override
    public <T> int updateNoNull(T t) {
        SqlDto sqlDto = buildSqlDto(t.getClass());
        String sql = buildUpdateSql(sqlDto, true);
        int effect = mapperFactory.update(sqlDto, sql, t);
        return effect;
    }

    /**
     * 删除
     */
    @Override
    public <T> int delete(T t) {
        SqlDto sqlDto = buildSqlDto(t.getClass());
        String sql = buildDeleteSql(sqlDto);
        int effect = mapperFactory.delete(sqlDto, sql, t);
        return effect;
    }

    /**
     * 保存实体列表
     *
     * @param list 实体列表
     * @return 修改记录
     */
    @Override
    public <T> int saveList(List<T> list, boolean ignoreRepeat) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        Class<?> aClass = list.get(0).getClass();
        SqlDto sqlDto = buildSqlDto(aClass);
        int effect = 0;
        if (ignoreRepeat) {
            effect = mapperFactory.insertListIgnore(sqlDto, list);
        } else {
            effect = mapperFactory.insertList(sqlDto, list);
        }
        return effect;
    }

    /**
     * 重置一个实体对象所有的字段为null值
     *
     * @param tClass 实体对象
     * @return 空对象
     */
    public static <T> T reset(Class<T> tClass) {
        try {
            T t = tClass.getDeclaredConstructor().newInstance();
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                if (!field.canAccess(t)) {
                    field.setAccessible(true);
                }
                field.set(t, null);
            }
            return t;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * 构建sqlDto
     *
     * @param tClass 实体类
     * @return sqlDto
     */
    public static <T> SqlDto buildSqlDto(Class<T> tClass) {
        String tableName = null;
        String id = null;
        String fields = null;//字段列表,多个使用,隔开
        String values = null;//值表,#{t.id},#{t.name}

        //表名
        Table table = tClass.getAnnotation(Table.class);
        if (table != null && table.value() != null) {
            tableName = table.value();
        }
        if (tableName == null || tableName.equals("")) {
            tableName = tClass.getSimpleName();
        }

        //字段名
        List<String> ids = new ArrayList<>();
        List<String> fieldNames = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        Field[] declaredFields = tClass.getDeclaredFields();
        String fieldName = null;
        String value = null;
        for (Field field : declaredFields) {
            fieldName = field.getName();
            value = String.join("", "#{t.", fieldName, "}");
            if (field.isAnnotationPresent(Id.class)) {
                ids.add(fieldName);
            }
            fieldNames.add(fieldName);
            valueList.add(value);
        }

        id = String.join(",", ids);
        fields = String.join(",", fieldNames);
        values = String.join(",", valueList);

        SqlDto sqlDto = new SqlDto();
        sqlDto.setTableName(tableName);
        sqlDto.setId(id);
        sqlDto.setFields(fields);
        sqlDto.setValues(values);

        return sqlDto;
    }

    /**
     * 构建updateSql
     *
     * @param sqlDto  sqlDto
     * @param notNull 是否忽略null值
     * @return updateSql
     */
    private String buildUpdateSql(SqlDto sqlDto, boolean notNull) {
        return null;
    }

    /**
     * 构建deleteSql
     *
     * @param sqlDto sqlDto
     * @return deleteSql
     */
    private String buildDeleteSql(SqlDto sqlDto) {
        return null;
    }
}
