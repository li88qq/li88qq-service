package com.li88qq.service.config.web.response;

/**
 * 响应异常
 *
 * @author li88qq
 * @version 1.0 2023/12/16 16:29
 */
public class ResponseException extends RuntimeException {

    private int code;//响应状态码
    private String msg;//信息

    public ResponseException() {
    }

    public ResponseException(String message) {
        super(message);
        this.msg = message;
    }

    public ResponseException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    /**
     * 不打印栈信息
     */
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
