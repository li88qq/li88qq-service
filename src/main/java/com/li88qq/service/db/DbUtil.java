package com.li88qq.service.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * db工具类
 *
 * @author li88qq
 * @version 1.0 2021/11/5 22:09
 */
public class DbUtil {

    /**
     * 返回id
     *
     * @param t
     * @param kClass
     * @param connection
     * @param <T>
     * @param <K>
     * @return
     */
    public <T, K> K saveId(T t, Class<K> kClass, Connection connection) throws Exception {
        Class<?> aClass = t.getClass();
        String tableName = BeanUtil.getTableName(aClass);
        List<String> fields = BeanUtil.getClassFields(aClass);

        String sql = BeanUtil.buildInsertSql(false, tableName, fields, 1);

        //不自动提交
        connection.setAutoCommit(false);
        //声明返回id
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int index = 1;
        Field declaredField = null;
        for (String field : fields) {
            declaredField = aClass.getDeclaredField(field);
            declaredField.setAccessible(true);
            statement.setObject(index++, declaredField.get(t));
        }
        long update = statement.executeLargeUpdate();
        if (update == 0) {
            return null;
        }
        ResultSet rs = statement.getGeneratedKeys();
        K id = null;
        while (rs.next()) {
            id = rs.getObject(1, kClass);
        }

        return id;
    }

    /**
     * 批量保存
     *
     * @param ignoreRepeat
     * @param tList
     * @param connection
     * @param <T>
     * @return
     */
    public <T> long saveBatch(boolean ignoreRepeat, List<T> tList, Connection connection) throws Exception {
        Class<?> aClass = tList.get(0).getClass();
        String tableName = BeanUtil.getTableName(aClass);
        List<String> fields = BeanUtil.getClassFields(aClass);

        int count = tList.size();
        String sql = BeanUtil.buildInsertSql(ignoreRepeat, tableName, fields, count);

        //不自动提交
        connection.setAutoCommit(false);
        //声明返回id
        PreparedStatement statement = connection.prepareStatement(sql);
        int index = 1;
        Field declaredField = null;
        for (T t : tList) {
            for (String field : fields) {
                declaredField = aClass.getDeclaredField(field);
                declaredField.setAccessible(true);
                statement.setObject(index++, declaredField.get(t));
            }
        }
        long update = statement.executeLargeUpdate();
        return update;
    }
}
