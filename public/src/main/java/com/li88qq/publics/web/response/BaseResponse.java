package com.li88qq.publics.web.response;

/**
 * 基本响应
 *
 * @author li88qq
 * @version 1.0 2023/12/16 16:27
 */
public class BaseResponse {

    private int code;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
