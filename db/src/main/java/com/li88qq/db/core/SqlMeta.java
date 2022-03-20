package com.li88qq.db.core;

/**
 * sql元数据
 *
 * @author li88qq
 * @version 1.0 2022/3/7 20:49
 */
class SqlMeta {

    private String sql; //sql语句
    private Object[] params;//参数列表

    public SqlMeta() {
    }

    public SqlMeta(String sql, Object[] params) {
        this.sql = sql;
        this.params = params;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
