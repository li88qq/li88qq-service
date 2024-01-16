package com.li88qq.gateway.filter;

import com.alibaba.fastjson2.JSON;
import com.li88qq.gateway.config.RouteWhite;
import com.li88qq.publics.session.SessionUtil;
import com.li88qq.publics.web.response.BaseResponse;
import com.li88qq.publics.web.response.ResponseCode;
import com.li88qq.publics.web.response.ResponseUtil;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * token拦截
 *
 * @author li88qq
 * @version 1.0 2023/12/27 23:22
 */
@Configuration
@Order(-1)
public class TokenFilter implements GlobalFilter {

    @Resource
    private RouteWhite routeWhite;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // 是否白名单
        if (whiteChain(path)) {
            return chain.filter(exchange);
        }

        if (!tokenChain(request)) {
            return errorResponse(exchange);
        }

        return chain.filter(exchange);
    }

    /**
     * 白名单
     *
     * @param path
     * @return
     */
    private boolean whiteChain(String path) {
        List<String> whiteReutes = routeWhite.getWhite();
        if (whiteReutes == null || whiteReutes.isEmpty()) {
            return false;
        }
        return whiteReutes.contains(path);
    }

    /**
     * 拦截
     */
    private boolean tokenChain(ServerHttpRequest request) {
        int uid = SessionUtil.getUid(request);
        return uid > 0;
    }

    /**
     * 返回未登录
     *
     * @param exchange
     * @return
     */
    private Mono<Void> errorResponse(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);//未认证
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        BaseResponse baseResponse = ResponseUtil.error(ResponseCode.NO_LOGIN);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONBytes(baseResponse, StandardCharsets.UTF_8.name()));
        return response.writeWith(Mono.just(dataBuffer));
    }
}
