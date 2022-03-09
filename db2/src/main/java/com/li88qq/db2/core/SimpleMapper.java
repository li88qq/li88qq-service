package com.li88qq.db2.core;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * mapper实现
 *
 * @author li88qq
 * @version 1.0 2022/3/6 23:28
 */
@Component
public class SimpleMapper implements Mapper {

    @Resource
    private SimpleQuery simpleQuery;

    /**
     * 保存
     */
    @Override
    public <T> int save(T t) {
        return simpleQuery.query(QueryType.INSERT, t);
    }

    /**
     * 保存,返回id
     */
    @Override
    public <T, K extends Number> K saveId(T t, Class<K> kClass) {
        return simpleQuery.queryId(QueryType.INSERT_ID, t, kClass);
    }

    /**
     * 修改
     */
    @Override
    public <T> int update(T t) {
        return simpleQuery.query(QueryType.UPDATE, t);
    }

    /**
     * 修改,只修改非空字段
     */
    @Override
    public <T> int updateNoNull(T t) {
        return simpleQuery.query(QueryType.UPDATE_NOT_NULL, t);
    }

    /**
     * 删除
     */
    @Override
    public <T> int delete(T t) {
        return simpleQuery.query(QueryType.DELETE, t);
    }

    /**
     * 保存实体列表
     *
     * @param list 实体列表
     * @return 修改记录
     */
    @Override
    public <T> int saveList(List<T> list, boolean ignoreRepeat) {
        return simpleQuery.saveList(list, ignoreRepeat);
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

}
