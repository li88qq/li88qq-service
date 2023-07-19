package com.li88qq.common.web.response;

/**
 * 统一响应类
 *
 * @author li88qq
 * @version 1.0 2023/7/19 22:52
 */
public class BaseResponse<T> {

    private int code;//状态码,0-成功
    private String msg;//响应信息
    private T data;//响应数据

    public BaseResponse() {
    }

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 是否成功
     *
     * @return 是否成功
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
