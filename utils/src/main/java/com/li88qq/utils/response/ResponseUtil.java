package com.li88qq.utils.response;

/**
 * 响应工具类
 *
 * @author li88qq
 * @version 1.0 2021/12/17 23:24
 */
public class ResponseUtil {

    /**
     * 响应正常
     *
     * @return 响应
     */
    public static BaseResponse ok() {
        return response(ResponseState.SUCCESS);
    }

    /**
     * 响应正常
     *
     * @param msg 响应信息
     * @return 响应
     */
    public static BaseResponse okMsg(String msg) {
        return response(ResponseState.SUCCESS, msg);
    }

    /**
     * 响应正常
     *
     * @param data 响应数据
     * @return 响应
     */
    public static BaseResponse ok(Object data) {
        ResponseState state = ResponseState.SUCCESS;
        return response(state.getCode(), state.getMsg(), data);
    }

    /**
     * 响应正常
     *
     * @param msg  响应信息
     * @param data 响应数据
     * @return 响应
     */
    public static BaseResponse ok(String msg, Object data) {
        return response(ResponseState.SUCCESS.getCode(), msg, data);
    }

    /**
     * 响应异常
     *
     * @param msg 响应信息
     * @return 响应
     */
    public static BaseResponse error(String msg) {
        return response(ResponseState.ERROR, msg);
    }

    /**
     * 响应异常
     *
     * @param state 响应状态
     * @return 响应
     */
    public static BaseResponse error(ResponseState state) {
        return response(state.getCode(), state.getMsg(), null);
    }

    /**
     * 响应
     *
     * @param state 响应状态
     * @return 响应
     */
    public static BaseResponse response(ResponseState state) {
        return response(state.getCode(), state.getMsg(), null);
    }

    /**
     * 响应
     *
     * @param state 响应状态
     * @param msg   响应信息
     * @return 响应
     */
    public static BaseResponse response(ResponseState state, String msg) {
        if (msg == null) {
            msg = state.getMsg();
        }
        return response(state.getCode(), msg, null);
    }

    /**
     * 响应
     *
     * @param code 响应码
     * @param msg  响应信息
     * @param data 响应数据
     * @return 响应
     */
    public static BaseResponse response(int code, String msg, Object data) {
        BaseResponse response = new BaseResponse();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    /**
     * 响应异常
     *
     * @param msg 异常信息
     * @return 抛出异常
     */
    public static ResponseException exception(String msg) {
        return new ResponseException(msg);
    }

}
