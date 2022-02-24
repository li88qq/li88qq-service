package com.li88qq.db.dto;

/**
 * sql元数据
 *
 * @author li88qq
 * @version 1.0 2022/2/24 23:26
 */
public class SqlMeta {

    private String sql;//sql语句,如果有参数,则用?代替
    private Object[] params;//sql参数

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
