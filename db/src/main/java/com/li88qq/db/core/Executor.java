package com.li88qq.db.core;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author li88qq
 * @version 1.0 2022/2/22 22:15
 */
public class Executor {

    /**
     * 查询
     *
     * @param connection 数据库连接
     * @param sql        sql
     * @param params     参数
     * @return 查询列表
     * @throws Exception 异常
     */
    public List<Map<String, Object>> query(Connection connection, String sql, Object[] params) throws Exception {
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        //参数设置
        setParams(prepareStatement, params);

        ResultSet resultSet = prepareStatement.executeQuery();
        if (resultSet == null) {
            return null;
        }
        //获取列名
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] labels = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
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
     * @param sql        sql
     * @param params     参数
     * @return 影响行数
     * @throws Exception 异常
     */
    public long execute(Connection connection, String sql, Object[] params) throws Exception {
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        //参数设置
        setParams(prepareStatement, params);
        return prepareStatement.executeLargeUpdate();
    }

    /**
     * 保存id
     *
     * @param sql    数据库连接
     * @param params sql
     * @param params 参数
     * @param <T>    id类型
     * @return 插入id
     */
    public <T> T executeId(Connection connection, String sql, Object[] params) {
        return null;
    }

    //设置参数
    private void setParams(PreparedStatement prepareStatement, Object[] params) throws Exception {
        if (params != null && params.length > 0) {
            int index = 1;
            for (Object param : params) {
                prepareStatement.setObject(index++, param);
            }
        }
    }

}
