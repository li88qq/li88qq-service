package com.li88qq.common.dto;

/**
 * 基本结果
 *
 * @author li88qq
 * @version 1.0 2023/7/23 17:51
 */
public class BaseResult<T> {

    private boolean success = true;//是否成功
    private String msg;//响应信息
    private T data;//数据

    /**
     * 成功
     */
    public static <T> BaseResult<T> ok(T data) {
        BaseResult<T> result = new BaseResult<>();
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static <T> BaseResult<T> error(String msg) {
        BaseResult<T> result = new BaseResult<>();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
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
