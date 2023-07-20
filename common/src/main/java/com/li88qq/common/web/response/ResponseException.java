package com.li88qq.common.web.response;

/**
 * 自定义响应异常
 *
 * @author li88qq
 * @version 1.0 2023/7/20 23:14
 */
public class ResponseException extends RuntimeException {

    private int code = ResponseCode.FAIL.getCode();
    private String msg;

    public ResponseException() {
    }

    public ResponseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ResponseException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.msg = responseCode.getMsg();
        this.code = responseCode.getCode();
    }

    @Override
    public void printStackTrace() {
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
