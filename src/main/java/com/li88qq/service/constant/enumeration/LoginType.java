package com.li88qq.service.constant.enumeration;

/**
 * 登录方式
 *
 * @author li88qq
 * @version 1.0 2021/8/4 23:05
 */
public enum LoginType {

    WEB(1, "浏览器"),
    MOBILE(2, "移动端"),
    WX(3, "微信扫一扫"),
    SMS(4,"手机短信"),

    ;

    private Integer type;
    private String text;

    LoginType(Integer type, String text) {
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
