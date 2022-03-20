package com.li88qq.db.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * 执行
 *
 * @author li88qq
 * @version 1.0 2022/3/6 23:46
 */
@Component
class SimpleQuery {

    @Autowired(required = false)
    private DataSource dataSource;

    /**
     * 执行更新操作
     */
    public <T> int query(QueryType queryType, T t) {
        if (t == null) {
            return 0;
        }
        SqlMeta sqlMeta = SqlFactory.buildSql(queryType, t);
        return execute(sqlMeta);
    }

    /**
     * 执行更新操作,返回自增长id
     */
    public <T, K extends Number> K queryId(QueryType queryType, T t, Class<K> kClass) {
        if (t == null || kClass == null) {
            return null;
        }
        SqlMeta sqlMeta = SqlFactory.buildSql(queryType, t);
        return executeId(sqlMeta, kClass);
    }

    //执行sql
    private int execute(SqlMeta sqlMeta) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlMeta.getSql());
            setParams(statement, sqlMeta.getParams());
            return statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
        }
    }

    //执行sql
    @SuppressWarnings("unchecked")
    private <K extends Number> K executeId(SqlMeta sqlMeta, Class<K> kClass) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlMeta.getSql(), Statement.RETURN_GENERATED_KEYS);
            setParams(statement, sqlMeta.getParams());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            K id = null;
            while (generatedKeys.next()) {
                id = generatedKeys.getObject(1, kClass);
            }
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
        }
    }

    //设置参数
    private void setParams(PreparedStatement statement, Object[] params) throws Exception {
        if (params == null || params.length == 0) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }

    //关闭连接
    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    /**
     * 批量保存
     *
     * @param list         实体列表
     * @param ignoreRepeat 是否忽略重复主键
     * @return 影响行数
     */
    public <T> int saveList(List<T> list, boolean ignoreRepeat) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        SqlMeta sqlMeta = SqlFactory.buildSaveListSql(list, ignoreRepeat);
        return execute(sqlMeta);
    }
}
