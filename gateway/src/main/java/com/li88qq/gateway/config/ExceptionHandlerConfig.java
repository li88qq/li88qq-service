package com.li88qq.gateway.config;

import com.alibaba.fastjson.JSON;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.bean.web.response.ResponseState;
import com.li88qq.bean.web.response.ResponseUtil;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 统一异常处理
 *
 * @author li88qq
 * @version 1.0 2022/1/26 22:02
 */
@Configuration
@Order(-1)
public class ExceptionHandlerConfig implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        String message = ex.getMessage();
        if (ex instanceof ResponseStatusException) {
            ResponseStatusException exception = (ResponseStatusException) ex;
            HttpStatus status = exception.getStatus();
            response.setStatusCode(status);

            message = handleMessage(message, status);
        }

        BaseResponse baseResponse = ResponseUtil.response(ResponseState.ERROR, message);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(baseResponse));
        }));
    }

    //处理异常信息
    private String handleMessage(String message, HttpStatus status) {
        switch (status) {
            case SERVICE_UNAVAILABLE -> message = "服务不可用!";
            case NOT_FOUND -> message = "404!";
            case INTERNAL_SERVER_ERROR -> message = "请求失败!请稍后再试!";
            default -> {
            }
        }
        return message;
    }
}
