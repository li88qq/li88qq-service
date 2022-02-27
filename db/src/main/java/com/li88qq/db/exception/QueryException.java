package com.li88qq.db.exception;

/**
 * query自定义异常
 *
 * @author li88qq
 * @version 1.0 2022/2/27 23:10
 */
public class QueryException extends RuntimeException {

    public QueryException(String msg) {
        super(msg);
    }
}
