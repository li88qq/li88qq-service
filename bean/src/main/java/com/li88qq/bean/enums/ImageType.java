package com.li88qq.bean.enums;

/**
 * 图片类型
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:38
 */
public enum ImageType {

    image(1, "图片"),
    article(2, "文章"),

    ;

    private int type;
    private String name;

    ImageType(int type, String name) {
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
