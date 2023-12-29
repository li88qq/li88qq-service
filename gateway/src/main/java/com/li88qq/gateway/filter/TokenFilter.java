package com.li88qq.gateway.filter;

import com.alibaba.fastjson2.JSON;
import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.common.web.response.ResponseCode;
import com.li88qq.common.web.response.ResponseUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 登录拦截
 *
 * @author li88qq
 * @version 1.0 2023/12/29 21:41
 */
@Component
@Order(value = -1)
public class TokenFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 拦截判断
        // 是否存在token
        // token是否过期
        return chain.filter(exchange);
    }

    /**
     * 响应未登录
     *
     * @param exchange
     * @return
     */
    private Mono<Void> error(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);//未认证
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);//json

        BaseResponse baseResponse = ResponseUtil.error(ResponseCode.NO_LOGIN);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONBytes(baseResponse));

        return response.writeWith(Mono.just(dataBuffer));
    }
}
