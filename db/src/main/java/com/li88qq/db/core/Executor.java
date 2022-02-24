package com.li88qq.db.core;

import com.li88qq.db.dto.SqlMeta;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sql执行器
 *
 * @author li88qq
 * @version 1.0 2022/2/22 22:15
 */
public class Executor {

    /**
     * 查询
     *
     * @param connection 数据库连接
     * @param sqlMeta    sql元数据
     * @return 查询列表
     * @throws SQLException 异常
     */
    public static List<Map<String, Object>> query(Connection connection, SqlMeta sqlMeta) throws SQLException {
        PreparedStatement prepareStatement = buildStatement(connection, sqlMeta, false);

        ResultSet resultSet = prepareStatement.executeQuery();
        if (resultSet == null) {
            return null;
        }
        //获取列名
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] labels = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            //获取实际的列名
            labels[i] = metaData.getColumnLabel(i + 1);
        }

        //结果封装
        List<Map<String, Object>> list = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<>();
            for (String key : labels) {
                map.put(key, resultSet.getObject(key));
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 更新
     *
     * @param connection 数据库连接
     * @param sqlMeta    sqlMeta sql元数据
     * @return 影响行数
     * @throws SQLException 异常
     */
    public static long execute(Connection connection, SqlMeta sqlMeta) throws SQLException {
        PreparedStatement prepareStatement = buildStatement(connection, sqlMeta, false);
        return prepareStatement.executeLargeUpdate();
    }

    /**
     * 保存id
     *
     * @param connection 数据库连接
     * @param sqlMeta    sqlMeta sql元数据
     * @return 插入id
     * @throws SQLException 异常
     */
    public static Long executeId(Connection connection, SqlMeta sqlMeta) throws SQLException {
        PreparedStatement prepareStatement = buildStatement(connection, sqlMeta, true);

        int update = prepareStatement.executeUpdate();
        if (update == 0) {
            return null;
        }
        ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
        if (generatedKeys == null) {
            return null;
        }
        Long id = null;
        while (generatedKeys.next()) {
            id = generatedKeys.getLong(1);
        }
        return id;
    }

    /**
     * 构建statement
     *
     * @param connection 数据库连接
     * @param sqlMeta    sqlMeta sql元数据
     * @param id         是否返回自增长id
     * @return statement
     * @throws SQLException 异常
     */
    private static PreparedStatement buildStatement(Connection connection, SqlMeta sqlMeta, boolean id) throws SQLException {
        String sql = sqlMeta.getSql();
        Object[] params = sqlMeta.getParams();
        //判断是否处理id
        PreparedStatement statement = null;
        if (!id) {
            statement = connection.prepareStatement(sql);
        } else {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }
        //设置参数
        if (params != null && params.length > 0) {
            int index = 1;
            for (Object param : params) {
                statement.setObject(index++, param);
            }
        }
        return statement;
    }

}
