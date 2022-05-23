package com.li88qq.bean.enums;

/**
 * 操作类型
 *
 * @author li88qq
 * @version 1.0 2022/5/23 23:45
 */
public enum ActionType {

    SAVE(1, "添加"),
    UPDATE(2, "修改"),
    DELETE(3, "删除"),
    QUERY(4, "查询"),
    FILE_IMPORT(5, "导入文件"),
    FILE_EXPORT(6, "导出文件"),

    ;
    private int type;
    private String name;

    ActionType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
