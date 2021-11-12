package com.li88qq.service.db;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * mapper实现类
 *
 * @author li88qq
 * @version 1.0 2021/11/2 23:03
 */
@Component
public class BaseMapperImpl implements BaseMapper {

    /**
     * 保存一个实体,返回实体
     *
     * @param t   实体对象
     * @param <T> 泛型
     * @return 实体对象
     */
    @Override
    public <T> T insert(T t) {
        return null;
    }

    /**
     * 保存一个实体,返回实体id
     *
     * @param t      实体对象
     * @param kClass id类型
     * @param <K>    泛型
     * @param <T>    泛型
     * @return 实体id
     */
    @Override
    public <K, T> K insertToId(T t, Class<K> kClass) {
        return null;
    }

    /**
     * 批量保存
     *
     * @param ignoreRepeat 是否忽略重复key
     * @param tList        实体列表
     * @param <T>          泛型
     * @return 影响行数
     */
    @Override
    public <T> long insertArray(boolean ignoreRepeat, List<T> tList) {
        return 0L;
    }

    /**
     * 更新一个实体,id字段必须有值
     *
     * @param t   实体对象
     * @param <T> 泛型
     * @return 影响行数
     */
    @Override
    public <T> int update(T t) {
        return 0;
    }

    /**
     * 批量更新,id字段必须有值
     *
     * @param tList 实体列表
     * @param <T>   泛型
     * @return 影响行数
     */
    @Override
    public <T> long updateArray(List<T> tList) {
        return 0L;
    }

    /**
     * 根据id,如果不存在,则保存,存在则修改
     *
     * @param t   实体对象
     * @param <T> 泛型
     * @return 实体对象
     */
    @Override
    public <T> T saveOrUpdate(T t) {
        return null;
    }

    /**
     * 更新一个实体,id字段必须有值,只更新值不为空的字段
     *
     * @param t   实体对象
     * @param <T> 泛型
     * @return 影响行数
     */
    @Override
    public <T> int executeUpdate(T t) {
        return 0;
    }
}
