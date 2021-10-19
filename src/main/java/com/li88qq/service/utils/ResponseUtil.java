package com.li88qq.service.utils;

import com.li88qq.service.constant.enumeration.ResponseState;
import com.li88qq.service.dto.BaseResponse;

/**
 * 响应工具类
 */
public class ResponseUtil {

    /**
     * 响应正常
     *
     * @return
     */
    public static BaseResponse ok() {
        return new BaseResponse();
    }

    /**
     * 响应正常-带信息
     *
     * @param msg
     * @return
     */
    public static BaseResponse okMsg(String msg) {
        return new BaseResponse(ResponseState.SUCCESS.getCode(), msg, null);
    }

    /**
     * 响应正常-带数据
     *
     * @param data
     * @return
     */
    public static BaseResponse ok(Object data) {
        return new BaseResponse(ResponseState.SUCCESS.getCode(), ResponseState.SUCCESS.getMsg(), data);
    }

    /**
     * 响应异常
     *
     * @param msg
     * @return
     */
    public static BaseResponse error(String msg) {
        if (msg == null) {
            msg = ResponseState.FAIL.getMsg();
        }
        return new BaseResponse(ResponseState.FAIL.getCode(), msg, null);
    }

    /**
     * 根据异常类型响应
     *
     * @param state
     * @return
     */
    public static BaseResponse response(ResponseState state) {
        BaseResponse response = new BaseResponse(state.getCode(), state.getMsg(), null);
        return response;
    }

    /**
     * 统一响应
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static BaseResponse response(int code, String msg, Object data) {
        return new BaseResponse(code, msg, data);
    }
}
