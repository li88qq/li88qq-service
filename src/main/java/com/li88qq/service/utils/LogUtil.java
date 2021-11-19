package com.li88qq.service.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log工具类
 *
 * @author li88qq
 * @version 1.0 2021/11/19 22:31
 */
public class LogUtil {

    /**
     * 获取当前logger
     *
     * @return logger
     */
    public static Logger getLogger() {
        return getLogger(2);
    }

    /**
     * 公共方法
     *
     * @param index 栈索引,区别:2-为直接返回logger,3-需要调用一次当前类(LogUtil)的当前方法(getLogger)
     * @return logger
     */
    private static Logger getLogger(int index) {
        return LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[index].getClassName());
    }

    public static void debug(String msg) {
        getLogger(3).debug(msg);
    }

    public static void debug(String msg, Throwable throwable) {
        getLogger(3).debug(msg, throwable);
    }

    public static void info(String msg) {
        getLogger(3).info(msg);
    }

    public static void info(String msg, Throwable throwable) {
        getLogger(3).info(msg, throwable);
    }

    public static void warn(String msg) {
        getLogger(3).warn(msg);
    }

    public static void warn(String msg, Throwable throwable) {
        getLogger(3).warn(msg, throwable);
    }

    public static void error(String msg) {
        getLogger(3).error(msg);
    }

    public static void error(String msg, Throwable throwable) {
        getLogger(3).error(msg, throwable);
    }
}
