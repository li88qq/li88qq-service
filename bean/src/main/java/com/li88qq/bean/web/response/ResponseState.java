package com.li88qq.bean.web.response;

/**
 * 响应状态
 *
 * @author li88qq
 * @version 1.0 2021/12/17 23:25
 */
public enum ResponseState {

    SUCCESS(0, "操作成功"),
    ERROR(1, "操作失败"),
    NOT_LOGGED_IN(2, "未登录"),
    UNAUTHORIZED(3, "未授权"),
    PARAM(4, "参数错误"),
    OTHER(7, "其他"),
    ;

    private final int code;//响应状态码
    private final String msg;//响应信息

    ResponseState(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
