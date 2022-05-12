package com.li88qq.bean.dto;

/**
 * 基本结果
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:44
 */
public class BaseResult<T> {

    private boolean success;//是否成功
    private String msg;//信息
    private T data;//响应数据

    public BaseResult() {
    }

    public BaseResult(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 响应成功
     */
    public static <T> BaseResult<T> ok(T t) {
        return new BaseResult<>(true, null, t);
    }

    /**
     * 响应失败
     */
    public static <T> BaseResult<T> error(String msg) {
        return new BaseResult<>(false, msg, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
