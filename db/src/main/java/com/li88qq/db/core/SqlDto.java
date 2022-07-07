package com.li88qq.db.core;

/**
 * sql对象
 *
 * @author li88qq
 * @version 1.0 2022/7/3 23:40
 */
public class SqlDto {

    private String tableName;//表名
    private String id;//id值
    private String fields;//字段列表,多个使用,隔开
    private String fields2;//去除掉id字段列表,多个使用,隔开
    private String values;//值表,#{t.id},#{t.name}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getFields2() {
        return fields2;
    }

    public void setFields2(String fields2) {
        this.fields2 = fields2;
    }
}
