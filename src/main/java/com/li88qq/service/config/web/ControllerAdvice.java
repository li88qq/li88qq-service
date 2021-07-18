package com.li88qq.service.config.web;

import com.li88qq.service.constant.enumeration.ResponseState;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.utils.ResponseUtil;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 统一异常处理
 */
@RestControllerAdvice
public class ControllerAdvice {

    //BindException 使用@Valid校验的参数
    //HttpMessageNotReadableException @RequestBody,如果该对象参数所有字段都不传
    @ExceptionHandler(value = {BindException.class, HttpMessageNotReadableException.class})
    public BaseResponse handleParamException(Exception e) {
        String msg = ResponseState.PARAMS.getMsg();
        Class<? extends Exception> eClass = e.getClass();
        if (eClass.equals(MethodArgumentNotValidException.class)) { //@Valid
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            List<ObjectError> errors = ex.getAllErrors();
            StringBuilder sb = new StringBuilder();
            errors.forEach(a -> {
                String message = a.getDefaultMessage();
                sb.append(message).append(";");
            });
            msg = sb.toString();
        }

        return ResponseUtil.response(ResponseState.PARAMS.getCode(), msg, null);
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse handleException(Exception e) {
        e.printStackTrace();
        return ResponseUtil.error("操作失败!");
    }
}
