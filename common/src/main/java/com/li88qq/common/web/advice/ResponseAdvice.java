package com.li88qq.common.web.advice;

import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.common.web.response.ResponseUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一返回结果
 *
 * @author li88qq
 * @version 1.0 2022/3/23 20:56
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 拦截判断
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 封装
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        //这里, 主要是一些流返回,比如404处理,自定义response处理
        if (body == null) {
            return null;
        }
        if (body instanceof BaseResponse) {
            return body;
        }

        BaseResponse result = ResponseUtil.ok(body);
        //如果返回类型为字符串,需转为字符串,现在项目全部返回json类型
//        if (body instanceof String) {
//            return JSON.toJSONString(result);
//        }
        return result;
    }
}
