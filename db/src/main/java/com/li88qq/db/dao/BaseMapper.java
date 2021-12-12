package com.li88qq.db.dao;

import com.li88qq.db.utils.DbUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 基本mapper
 *
 * @author li88qq
 * @version 1.0 2021/12/11 22:59
 */
@Component
public class BaseMapper {

    @Resource
    private DataSource dataSource;

    /**
     * insert记录,返回主键id
     *
     * @param t       实体
     * @param idClass 主键类型
     * @param <T>     实体泛型
     * @param <K>     主键id泛型
     * @return 主键
     */
    public <T, K> K insert(T t, Class<K> idClass) {
        return DbUtil.insert(t, idClass, getConnection());
    }

    /**
     * insert记录,返回影响行数
     *
     * @param t   实体
     * @param <T> 实体泛型
     * @return 插入记录数
     */
    public <T> long insert(T t) {
        return DbUtil.insert(false, List.of(t), getConnection());
    }

    /**
     * insert多条记录,返回影响行数
     *
     * @param ignoreRepeat 是否忽略重复
     * @param list         记录
     * @param <T>          泛型
     * @return 插入记录数
     */
    public <T> long insert(boolean ignoreRepeat, List<T> list) {
        return DbUtil.insert(ignoreRepeat, list, getConnection());
    }

    /**
     * 修改记录
     *
     * @param t   实体
     * @param <T> 实体泛型
     * @return 影响行数
     */
    public <T> long update(T t) {
        return DbUtil.update(t, getConnection());
    }

    /**
     * 批量修改记录
     *
     * @param list 实体列表
     * @param <T>  实体泛型
     * @return 影响行数
     */
    public <T> long update(List<T> list) {
        return DbUtil.update(list, getConnection());
    }

    /**
     * saveOrUpdate
     *
     * @param t   实体对象
     * @param <T> 对象泛型
     * @return 影响行数
     */
    public <T> long saveOrUpdate(T t) {
        return DbUtil.saveOrUpdate(t, getConnection());
    }

    /**
     * 删除
     *
     * @param t   实体对象
     * @param <T> 对象泛型
     * @return 影响行数
     */
    public <T> long delete(T t) {
        return DbUtil.delete(t, getConnection());
    }

    /**
     * 获取连接
     *
     * @return 连接
     */
    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
