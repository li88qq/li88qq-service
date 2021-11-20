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
        return getLogger(false);
    }

    /**
     * 公共方法
     *
     * @param inner 栈索引,区别:false-为直接返回logger,此时取栈底索引,true-需要调用一次当前类(LogUtil)的当前方法(getLogger)
     * @return logger
     */
    private static Logger getLogger(boolean inner) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int index = inner ? 3 : stackTrace.length - 1;
        return LoggerFactory.getLogger(stackTrace[index].getClassName());
    }

    public static void debug(String msg) {
        getLogger(true).debug(msg);
    }

    public static void debug(String msg, Throwable throwable) {
        getLogger(true).debug(msg, throwable);
    }

    public static void info(String msg) {
        getLogger(true).info(msg);
    }

    public static void info(String msg, Throwable throwable) {
        getLogger(true).info(msg, throwable);
    }

    public static void warn(String msg) {
        getLogger(true).warn(msg);
    }

    public static void warn(String msg, Throwable throwable) {
        getLogger(true).warn(msg, throwable);
    }

    public static void error(String msg) {
        getLogger(true).error(msg);
    }

    public static void error(String msg, Throwable throwable) {
        getLogger(true).error(msg, throwable);
    }
}
