package com.li88qq.common.web.response;

/**
 * 统一响应状态码
 *
 * @author li88qq
 * @version 1.0 2023/7/19 22:56
 */
public enum ResponseCode {

    SUCCESS(0, "操作成功"),//成功

    NO_LOGIN(1, "未登录"),//未登录
    NO_AUTH(2, "未授权"),//未授权
    SINGLE_SIGN_ON(3, "单点登录"),//单点登录
    PARAM(6, "参数错误"),//参数错误
    FAIL(7, "操作失败"),//操作失败
    ;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

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
