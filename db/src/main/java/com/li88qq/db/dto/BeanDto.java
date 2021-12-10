package com.li88qq.db.dto;

/**
 * 实体分析
 *
 * @author li88qq
 * @version 1.0 2021/12/10 23:04
 */
public class BeanDto {

    private String tableName;//表名
    private String[] idNames;//id字段列表
    private String[] fields;//字段列表

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getIdNames() {
        return idNames;
    }

    public void setIdNames(String[] idNames) {
        this.idNames = idNames;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }
}
