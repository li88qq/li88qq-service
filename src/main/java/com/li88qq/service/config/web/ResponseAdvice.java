package com.li88qq.service.config.web;

import com.alibaba.fastjson.JSON;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.utils.ResponseUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * 统一响应处理
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        if (method != null) {
            Class<?> returnType = method.getReturnType();
            return !returnType.equals(BaseResponse.class);
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        BaseResponse response = ResponseUtil.ok(body);
        Class<?> aClass1 = body.getClass();
        if (aClass1.equals(String.class)) {
            serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return JSON.toJSONString(response);
        }
        return response;
    }
}
