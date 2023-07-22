package com.li88qq.common.web.response;

/**
 * 响应工具类
 *
 * @author li88qq
 * @version 1.0 2023/7/19 23:15
 */
public class ResponseUtil {

    /**
     * 响应成功
     */
    public static BaseResponse ok() {
        return new BaseResponse();
    }

    /**
     * 响应成功,带信息
     */
    public static BaseResponse okMsg(String msg) {
        return new BaseResponse(ResponseCode.SUCCESS.getCode(), msg, null);
    }

    /**
     * 响应成功,带数据
     */
    public static BaseResponse ok(Object data) {
        return new BaseResponse(ResponseCode.SUCCESS.getCode(), null, data);
    }

    /**
     * 响应失败
     */
    public static BaseResponse error(String msg) {
        return new BaseResponse(ResponseCode.FAIL.getCode(), msg, null);
    }

    /**
     * 响应失败
     */
    public static BaseResponse error(ResponseCode code) {
        return new BaseResponse(code.getCode(), null, null);
    }

    /**
     * 响应失败
     */
    public static BaseResponse error(ResponseCode code, String msg) {
        return new BaseResponse(code.getCode(), msg, null);
    }

    /**
     * 响应
     */
    public static <T> BaseResponse response(int code, String msg, Object data) {
        return new BaseResponse(code, msg, data);
    }

    /**
     * 抛出异常
     */
    public static void exception(String msg) throws ResponseException {
        throw new ResponseException(msg);
    }

    /**
     * 抛出异常
     */
    public static void exception(ResponseCode responseCode) throws ResponseException {
        throw new ResponseException(responseCode);
    }

}
