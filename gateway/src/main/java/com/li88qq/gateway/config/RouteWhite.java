package com.li88qq.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 白名单
 *
 * @author li88qq
 * @version 1.0 2023/12/28 22:49
 */
@Component
@ConfigurationProperties(prefix = "route")
public class RouteWhite {

    private List<String> white;

    public List<String> getWhite() {
        return white;
    }

    public void setWhite(List<String> white) {
        this.white = white;
    }
}
