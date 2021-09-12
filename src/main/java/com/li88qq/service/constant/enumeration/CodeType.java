package com.li88qq.service.constant.enumeration;

/**
 * 题目类型
 *
 * @author li88qq
 * @version 1.0 2021/9/12 23:08
 */
public enum CodeType {

    JUDGE(1, "判断题"),
    SINGLE_CHOICE(2, "单选"),
    MULTIPLE_CHOICE(3, "多选"),
    INPUT(4, "输入框"),
    QA(5, "问答题"),
    CODE(6, "编码"),

    ;

    private Integer type;
    private String name;

    CodeType(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static CodeType parse(Integer type) {
        if (type == null) {
            return null;
        }
        for (CodeType codeType : CodeType.values()) {
            if (codeType.getType().equals(type)) {
                return codeType;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
