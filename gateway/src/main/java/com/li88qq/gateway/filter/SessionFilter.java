package com.li88qq.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.li88qq.bean.web.redis.RedisKey;
import com.li88qq.bean.web.redis.RedisUtil;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.bean.web.response.ResponseState;
import com.li88qq.bean.web.response.ResponseUtil;
import com.li88qq.bean.web.session.SessionUtil;
import com.li88qq.bean.web.session.UserToken;
import com.li88qq.gateway.config.ExcludeRoutes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * session过滤器
 *
 * @author li88qq
 * @version 1.0 2022/1/15 23:00
 */
@Component
public class SessionFilter implements GlobalFilter {

    private static final Logger LOG = LogManager.getLogger();
    @Resource
    private RedisUtil redisUtil;

    /**
     * 拦截
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        //如果为忽略的路径
        if (ExcludeRoutes.routes.contains(path)) {
            return chain.filter(exchange);
        }

        if (path.startsWith("/p")) {
            return filterMain(exchange, chain);
        } else if (path.startsWith("/am")) {
            return filterAdmin(exchange, chain);
        }

        return chain.filter(exchange);
    }

    //前台
    private Mono<Void> filterMain(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        List<String> list = request.getHeaders().get("ptoken");
        if (list == null || list.isEmpty()) {
            return handleToken(response);
        }
        String amToken = list.get(0);
        UserToken userToken = redisUtil.get(RedisKey.P_USER_TOKEN, amToken, UserToken.class);
        if (userToken == null) {
            return handleToken(response);
        }
        SessionUtil.updateSession();
        return chain.filter(exchange);
    }

    //管理后台
    private Mono<Void> filterAdmin(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        List<String> list = request.getHeaders().get("amtoken");
        if (list == null || list.isEmpty()) {
            return handleToken(response);
        }
        String amToken = list.get(0);
        UserToken userToken = redisUtil.get(RedisKey.AM_USER_TOKEN, amToken, UserToken.class);
        if (userToken == null) {
            return handleToken(response);
        }
        redisUtil.expire(RedisKey.AM_USER_TOKEN, userToken.getToken(), 5, 30);
        return chain.filter(exchange);
    }

    //401响应
    private Mono<Void> handleToken(ServerHttpResponse response) {
        BaseResponse baseResponse = ResponseUtil.response(ResponseState.NOT_LOGGED_IN);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(baseResponse));
        }));
    }

}
