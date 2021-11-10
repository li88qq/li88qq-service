package com.li88qq.service.db;

import java.sql.Connection;

/**
 * 本地线程工具
 *
 * @author li88qq
 * @version 1.0 2021/11/10 23:57
 */
public class ThreadLocalUtil {

    private static final ThreadLocal<Connection> ctx = new ThreadLocal<>();

    public static void set(Connection connection) {
        ctx.set(connection);
    }

    public static Connection get() {
        return ctx.get();
    }

    public static void remove() {
        ctx.set(null);
        ctx.remove();
    }
}
