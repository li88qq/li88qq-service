package com.li88qq.db.dto.bean;

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
}
