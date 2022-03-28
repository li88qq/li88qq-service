package com.li88qq.gateway.config;

import java.util.HashSet;
import java.util.Set;

/**
 * @author li88qq
 * @version 1.0 2022/3/28 17:06
 */
public class ExcludeRoutes {

    public static Set<String> routes = new HashSet<>();

    static {
        routes.add("/p/getCaptcha");
        routes.add("/p/login");
        routes.add("/p/logout");
        routes.add("/p/register");

        routes.add("/am/login");
        routes.add("/am/logout");
        routes.add("/am/getSms");
    }
}
