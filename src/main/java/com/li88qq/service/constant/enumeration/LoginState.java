package com.li88qq.service.constant.enumeration;

/**
 * 登录状态
 *
 * @author li88qq
 * @version 1.0 2021/8/4 22:44
 */
public enum LoginState {

    SUCCESS(1, "成功"),
    NOT_PASSWORD(2, "密码错误"),
    NOT_USER(3, "用户不存在"),

    ;

    private Integer state;
    private String text;

    LoginState(Integer state, String text) {
        this.state = state;
        this.text = text;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
