package com.li88qq.utils.response;

/**
 * 自定义异常
 *
 * @author li88qq
 * @version 1.0 2022/3/22 23:28
 */
public class ResponseException extends RuntimeException {

    public ResponseException(String msg) {
        super(msg);
    }

    @Override
    public void printStackTrace() {
        return;
    }
}
