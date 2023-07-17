package com.li88qq.db.dto.sql;

/**
 * 条件dto
 *
 * @author li88qq
 * @version 1.0 2023/3/5 22:23
 */
public class NodeDto {

    private String key;//键
    private Object value;//值

    private Object formatValue;//格式化后的值

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getFormatValue() {
        return formatValue;
    }

    public void setFormatValue(Object formatValue) {
        this.formatValue = formatValue;
    }
}
