package com.li88qq.db.dto.sql;

/**
 * sql传输对象
 *
 * @author li88qq
 * @version 1.0 2023/2/21 22:39
 */
public class SqlDto {

    private String table;//表名,如果是指定数据库,则表名为db.table
    private String keys;//insert 字段
    private String values;//insert 值
    private String ignore;//是否忽略重复,insert列表
    private String where;//where字段,update,delete
    private String set;//set字段,update
    private String idField;//id字段值,对应实体
    private String idColumn;//id字段列,对应数据库列
    private String[] setFields;//updateList field
    private String[] setColumns;//updateList column

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getIgnore() {
        return ignore;
    }

    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(String idColumn) {
        this.idColumn = idColumn;
    }

    public String[] getSetFields() {
        return setFields;
    }

    public void setSetFields(String[] setFields) {
        this.setFields = setFields;
    }

    public String[] getSetColumns() {
        return setColumns;
    }

    public void setSetColumns(String[] setColumns) {
        this.setColumns = setColumns;
    }
}
