package com.li88qq.bean.web.response;

/**
 * 自定义异常
 *
 * @author li88qq
 * @version 1.0 2022/3/22 23:28
 */
public class ResponseException extends RuntimeException {

    private int code;
    private String msg;

    public ResponseException(String msg) {
        this.code = ResponseState.ERROR.getCode();
        this.msg = msg;
    }

    public ResponseException(ResponseState responseState) {
        this.code = responseState.getCode();
        this.msg = responseState.getMsg();
    }

    @Override
    public void printStackTrace() {
        return;
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
