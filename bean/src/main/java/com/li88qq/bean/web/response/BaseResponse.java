package com.li88qq.bean.web.response;

/**
 * 基本响应类
 *
 * @author li88qq
 * @version 1.0 2021/12/17 23:21
 */
public class BaseResponse {

    private int code;//状态,0:正常
    private String msg;//响应信息
    private Object data;//响应数据

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
