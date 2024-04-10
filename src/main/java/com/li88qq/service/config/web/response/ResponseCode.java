package com.li88qq.service.config.web.response;

/**
 * 响应状态码
 *
 * @author li88qq
 * @version 1.0 2023/12/16 16:33
 */
public enum ResponseCode {
    SUCCESS(0, "操作成功"),
    FAIL(1, "操作失败"),
    NO_LOGIN(2, "未登录"),
    NO_AUTH(3, "未授权"),
    PARAM(4, "参数错误"),
    DATA(5, "数据错误"),

    BUSINESS(8, "业务操作"),//业务操作,需要进一步判断

    ERROR(9, "系统异常"),
    ;

    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
