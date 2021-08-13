package com.li88qq.service.constant.enumeration;

/**
 * 操作类型
 *
 * @author li88qq
 * @version 1.0 2021/8/11 23:30
 */
public enum ActionType {

    QUERY(1, "查询"),
    SAVE(2, "新增"),
    UPDATE(3, "修改"),
    DELETE(4, "删除"),
    FILE(5, "文件"),

    ;

    private Integer type;
    private String text;

    ActionType(Integer type, String text) {
        this.type = type;
        this.text = text;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
