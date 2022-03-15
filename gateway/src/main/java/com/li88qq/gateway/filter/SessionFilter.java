package com.li88qq.gateway.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * session过滤器
 *
 * @author li88qq
 * @version 1.0 2022/1/15 23:00
 */
public class SessionFilter implements GlobalFilter, Ordered {

    private static final Logger LOG = LogManager.getLogger();

    private final int order;

    public SessionFilter(int order) {
        this.order = order;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOG.info("session");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return this.order;
    }
}
