package com.li88qq.db.core;

/**
 * 实体对象元数据
 *
 * @author li88qq
 * @version 1.0 2022/3/7 21:25
 */
class ClassMeta {

    private String tableName;//表名
    private String[] ids;//id字段列表
    private String[] fields;//其他字段列表

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }
}
