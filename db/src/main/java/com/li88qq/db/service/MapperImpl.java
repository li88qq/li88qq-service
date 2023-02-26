package com.li88qq.db.service;

import com.li88qq.db.dao.MapperTemplate;
import com.li88qq.db.dto.sql.SqlDto;
import com.li88qq.db.utils.SqlDtoBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * mapper实现
 *
 * @author li88qq
 * @version 1.0 2023/2/21 21:42
 */
@Component
public class MapperImpl implements Mapper {

    @Resource
    private MapperTemplate mapperTemplate;

    /**
     * 新增
     *
     * @param t 实体
     * @return 影响行数
     */
    @Override
    public <T> int insertNoId(T t) {
        Class<?> aClass = t.getClass();
        SqlDto sqlDto = SqlDtoBuilder.buildInsert(aClass, false);
        return mapperTemplate.insertNoId(sqlDto, t);
    }

    /**
     * 新增
     *
     * @param t       实体
     * @param idClass id类型
     * @return 自增id
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T, K> K insertId(T t, Class<K> idClass) {
        Class<?> aClass = t.getClass();
        SqlDto sqlDto = SqlDtoBuilder.buildInsert(aClass, false);

        String idField = sqlDto.getIdField();
        Assert.isTrue(idField != null, "该方法需要一个@Id且仅一个声明字段");
        mapperTemplate.insertId(sqlDto, t);
        Field field = null;
        try {
            field = aClass.getDeclaredField(idField);
            field.setAccessible(true);
            Object id = field.get(t);
            return (K) id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改
     *
     * @param t 实体
     * @return 影响行数
     */
    @Override
    public <T> int update(T t) {
        Class<?> aClass = t.getClass();
        SqlDto sqlDto = SqlDtoBuilder.buildUpdate(aClass);
        return mapperTemplate.update(sqlDto, t);
    }

    /**
     * 修改
     *
     * @param t 实体,只修改非空字段
     * @return 影响行数
     */
    @Override
    public <T> int updateNotNull(T t) {
        return 0;
    }

    /**
     * 删除
     *
     * @param t 实体
     * @return 影响行数
     */
    @Override
    public <T> int delete(T t) {
        Class<?> aClass = t.getClass();
        SqlDto sqlDto = SqlDtoBuilder.buildUpdate(aClass);
        return mapperTemplate.delete(sqlDto, t);
    }

    /**
     * 新增(批量)
     *
     * @param list         列表
     * @param ignoreRepeat 是否忽略主键重复
     * @return 影响行数
     */
    @Override
    public <T> int insertList(List<T> list, boolean ignoreRepeat) {
        Class<?> aClass = list.get(0).getClass();
        SqlDto sqlDto = SqlDtoBuilder.buildInsert(aClass, ignoreRepeat);
        return mapperTemplate.insertList(sqlDto, list);
    }
}
