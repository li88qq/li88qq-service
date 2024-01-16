package com.li88qq.gateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由定义
 * @author li88qq
 * @version 1.0 2023/12/16 11:04
 */
@Configuration
public class RoutesConfig {

    /**
     * 构建路由
     *
     * @param r      PredicateSpec
     * @param prefix api前缀
     * @param app    服务app
     * @return router
     */
    private Buildable<Route> initRoute(PredicateSpec r, String prefix, String app) {
        String sep = "/";
        String path = String.join("", prefix, sep, app, "/**");
        String rePath = String.join("", prefix, sep, app, "/(?<segment>.*)");
        String regex = "/$\\{segment}";
        String lb = String.join("", "lb://", app);
        return r.path(path).filters(f -> f.rewritePath(rePath, regex)).uri(lb);
    }

    /**
     * 定义路由
     *
     * @param builder RouteLocatorBuilder
     * @return 路由
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        String prefix = "/api";
        return builder.routes()
                .route("main", r -> initRoute(r, prefix, "main"))
                .route("login", r -> initRoute(r, prefix, "login"))
                .build();
    }
}
