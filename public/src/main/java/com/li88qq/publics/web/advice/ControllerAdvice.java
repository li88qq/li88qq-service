package com.li88qq.publics.web.advice;

import com.alibaba.fastjson2.JSON;
import com.li88qq.publics.web.response.BaseResponse;
import com.li88qq.publics.web.response.ResponseCode;
import com.li88qq.publics.web.response.ResponseException;
import com.li88qq.publics.web.response.ResponseUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 统一异常处理
 *
 * @author li88qq
 * @version 1.0 2023/12/16 16:51
 */
@RestControllerAdvice
public class ControllerAdvice {

    private static final Logger LOG = LogManager.getLogger();
    @Resource
    private HttpServletResponse response;

    /**
     * 参数异常处理
     * <p>
     * BindException:每个参数字段校验
     * HttpMessageNotReadableException:整个校验对象没传
     * MissingServletRequestParameterException:get方法参数
     * </p>
     */
    @ExceptionHandler(value = {BindException.class, HttpMessageNotReadableException.class, MissingServletRequestParameterException.class})
    public BaseResponse handeBindException(Exception ex) {
        //如果是校验参数错误,只返回一个
        if (ex instanceof BindException) {
            BindException exception = (BindException) ex;
            String msg = exception.getAllErrors().get(0).getDefaultMessage();
            if (msg != null && !msg.equals("")) {
                return ResponseUtil.error(msg);
            }
        }

        return ResponseUtil.error(ResponseCode.PARAM);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = ResponseException.class)
    public BaseResponse handleResponseException(Exception ex) {
        ResponseException exception = (ResponseException) ex;
        return ResponseUtil.response(exception.getCode(), exception.getMsg(), null);
    }

    /**
     * 请求方法错误
     * <p>如POST接口,使用GET请求</p>
     */
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public BaseResponse handleHttpRequestMethodNotSupportedException(Exception ex) {
        return ResponseUtil.error(ex.getMessage());
    }

    /**
     * 404
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public void handleNoHandlerFoundException(Exception ex) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        BaseResponse baseResponse = ResponseUtil.response(404, "not found", null);
        try {
            response.getWriter().write(JSON.toJSONString(baseResponse));
        } catch (IOException e) {
        }
    }

    /**
     * 统一异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse handleException(Exception ex) {
        LOG.error(ex);
        return ResponseUtil.error(ResponseCode.FAIL);
    }
}
