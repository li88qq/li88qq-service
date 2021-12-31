package com.li88qq.db.dto;

/**
 * bean对象分析
 *
 * @author li88qq
 * @version 1.0 2021/12/31 22:55
 */
public class BeanDto {

    private String tableName;//表名
    private String[] idNames;//id名称列表
    private String[] fieldNames;//字段名称列表

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

    public String[] getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(String[] fieldNames) {
        this.fieldNames = fieldNames;
    }
}
