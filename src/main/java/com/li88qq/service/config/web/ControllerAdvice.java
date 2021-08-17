package com.li88qq.service.config.web;

import com.li88qq.service.constant.enumeration.ResponseState;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.entity.ErrorLog;
import com.li88qq.service.repo.ErrorLogRepo;
import com.li88qq.service.utils.ResponseUtil;
import com.li88qq.service.utils.SessionUtil;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.util.List;

/**
 * 统一异常处理
 */
@RestControllerAdvice
public class ControllerAdvice {

    @Resource
    private ErrorLogRepo errorLogRepo;

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

        ErrorLog errorLog = new ErrorLog();
        errorLog.setExName(e.getClass().getName());
        errorLog.setMsg(e.getMessage());
        Long uid = errorLog.getUid();
        if (uid != null) {
            errorLog.setUid(uid);
            errorLog.setIp(SessionUtil.getIp());
        }
        try {
            errorLogRepo.save(errorLog);
        } catch (Exception exception) {

        }
        return ResponseUtil.error("操作失败!");
    }
}
