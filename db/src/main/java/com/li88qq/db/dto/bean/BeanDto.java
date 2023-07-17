package com.li88qq.db.dto.bean;

import java.util.Map;

/**
 * 实体转换对象
 *
 * @author li88qq
 * @version 1.0 2023/2/22 22:24
 */
public class BeanDto {

    private String[] ids;//声明了id的字段
    private String[] fields;//除id字段外的字段
    private String[] allFields;//全部字段,包括id及非id
    private Map<String, String> columnMap;//column对应map,key为数据库列,value为实体字段

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

    public String[] getAllFields() {
        return allFields;
    }

    public void setAllFields(String[] allFields) {
        this.allFields = allFields;
    }

    public Map<String, String> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, String> columnMap) {
        this.columnMap = columnMap;
    }
}
