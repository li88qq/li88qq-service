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

    /**
     * 转换
     *
     * @param type 类型
     * @return 图片类型
     */
    public static ImageType parse(Integer type) {
        if (type == null || type <= 0) {
            return null;
        }
        for (ImageType imageType : ImageType.values()) {
            if (imageType.getType() == type) {
                return imageType;
            }
        }
        return null;
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
