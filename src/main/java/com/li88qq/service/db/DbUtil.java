package com.li88qq.service.db;

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
    public <T, K> K saveId(T t, Class<K> kClass, Connection connection) {
        return null;
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
    public <T> long saveBatch(boolean ignoreRepeat, List<T> tList, Connection connection) {
        return 0L;
    }


    /**
     * 返回id
     *
     * @param connection
     * @param sql
     * @param params
     * @param tClass
     * @param <T>
     * @return
     * @throws Exception
     */
    private <T> T executeId(Connection connection, String sql, Object[] params, Class<T> tClass) throws Exception {
        //不自动提交
        connection.setAutoCommit(false);
        //声明返回id
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int index = 1;
        for (Object param : params) {
            statement.setObject(index++, param);
        }
        long update = statement.executeLargeUpdate();
        if (update == 0) {
            return null;
        }
        ResultSet rs = statement.getResultSet();
        return rs.getObject(1, tClass);
    }

    /**
     * 返回影响行数
     *
     * @param connection
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    private long executeBatch(Connection connection, String sql, Object[] params) throws Exception {
        //不自动提交
        connection.setAutoCommit(false);
        //声明返回id
        PreparedStatement statement = connection.prepareStatement(sql);
        int index = 1;
        for (Object param : params) {
            statement.setObject(index++, param);
        }
        return statement.executeLargeUpdate();
    }
}
