package com.li88qq.service.utils;

import com.li88qq.service.constant.enumeration.ResponseState;
import com.li88qq.service.dto.BaseResponse;

/**
 * 响应工具类
 */
public class ResponseUtil {

    public static BaseResponse ok() {
        return new BaseResponse();
    }

    public static BaseResponse okMsg(String msg) {
        return new BaseResponse(ResponseState.SUCCESS.getCode(), msg, null);
    }

    public static BaseResponse ok(Object data) {
        return new BaseResponse(ResponseState.SUCCESS.getCode(), ResponseState.SUCCESS.getMsg(), data);
    }

    public static BaseResponse error(String msg) {
        if (msg == null) {
            msg = ResponseState.FAIL.getMsg();
        }
        return new BaseResponse(ResponseState.FAIL.getCode(), msg, null);
    }

    public static BaseResponse response(ResponseState state) {
        BaseResponse response = new BaseResponse(state.getCode(), state.getMsg(), null);
        return response;
    }

    public static BaseResponse response(int code, String msg, Object data) {
        return new BaseResponse(code, msg, data);
    }
}
