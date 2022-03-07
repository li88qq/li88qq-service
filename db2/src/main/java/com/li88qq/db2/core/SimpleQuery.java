package com.li88qq.db2.core;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;

/**
 * 执行
 *
 * @author li88qq
 * @version 1.0 2022/3/6 23:46
 */
@Component
public class SimpleQuery {

    @Resource
    private SqlSession session;

    /**
     * 执行更新操作
     */
    public <T> int query(QueryType queryType, T t) {
        SqlMeta sqlMeta = SqlFactory.buildSql(queryType, t);
        return execute(sqlMeta);
    }

    /**
     * 执行更新操作,返回自增长id
     */
    public <T, K extends Number> K queryId(QueryType queryType, T t, Class<K> kClass) {
        SqlMeta sqlMeta = SqlFactory.buildSql(queryType, t);
        return executeId(sqlMeta, kClass);
    }

    /**
     * 执行批量更新操作
     */
    public <T> int queryBatch(QueryType queryType, List<T> list) {
        SqlMeta sqlMeta = SqlFactory.buildBatchSql(queryType, list);
        return execute(sqlMeta);
    }


    //执行sql
    private int execute(SqlMeta sqlMeta) {
        Connection connection = null;
        try {
            connection = session.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlMeta.getSql());
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
            connection = session.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlMeta.getSql(), Statement.RETURN_GENERATED_KEYS);
            setParams(statement, sqlMeta.getParams());
            statement.executeUpdate();
            return (K) statement.getGeneratedKeys().getObject(1);
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
            statement.setObject(i, params[i]);
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

}
