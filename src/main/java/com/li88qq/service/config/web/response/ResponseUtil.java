package com.li88qq.service.config.web.response;

/**
 * 响应工具类
 *
 * @author li88qq
 * @version 1.0 2023/12/16 16:29
 */
public class ResponseUtil {

    /**
     * 成功
     */
    public static BaseResponse ok() {
        return response(ResponseCode.SUCCESS, null, null);
    }

    /**
     * 成功,带信息
     */
    public static BaseResponse okMsg(String msg) {
        return response(ResponseCode.SUCCESS, msg, null);
    }

    /**
     * 成功,带数据
     */
    public static BaseResponse ok(Object data) {
        return response(ResponseCode.SUCCESS, null, data);
    }

    /**
     * 失败
     */
    public static BaseResponse error(String msg) {
        return response(ResponseCode.FAIL, msg, null);
    }

    /**
     * 失败
     */
    public static BaseResponse error(ResponseCode code) {
        return response(code.getCode(), code.getMsg(), null);
    }

    /**
     * 响应
     */
    public static BaseResponse response(ResponseCode code, String msg, Object data) {
        return response(code.getCode(), msg, data);
    }

    /**
     * 响应
     */
    public static BaseResponse response(int code, String msg, Object data) {
        BaseResponse response = new BaseResponse();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }
}
