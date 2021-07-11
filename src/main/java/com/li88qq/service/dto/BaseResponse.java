package com.li88qq.service.dto;

/**
 * 基本响应类
 */
public class BaseResponse {

    private int code;
    private String msg;
    private Object data;

    public BaseResponse() {
    }

    public BaseResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean success() {
        return this.code == 0;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
