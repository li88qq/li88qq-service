package com.li88qq.service.constant.enumeration;

/**
 * 响应状态
 */
public enum ResponseState {

    SUCCESS(0, "操作成功"),
    FAIL(1, "操作失败"),
    NO_LOGIN(2, "未登录"),
    NO_AUTHORIZE(3, "未授权"),
    NOT_FOUNT(4, "数据不存在"),
    PARAMS(5, "参数错误"),
    ERROR(9, "系统异常"),
    ;

    private int code;
    private String msg;

    ResponseState(int code, String msg) {
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
