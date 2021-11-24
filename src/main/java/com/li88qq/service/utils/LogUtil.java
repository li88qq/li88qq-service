package com.li88qq.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        return initLogger();
    }

    /**
     * 公共方法
     *
     * @return logger
     */
    private static Logger initLogger() {
        //栈
        //0-Thread.currentThread().getStackTrace()
        //1-LogUtil.initLogger()
        //2-LogUtil.debug()...info()...warn()...error...getLogger()
        //3-调用类
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    public static void debug(String msg) {
        initLogger().debug(msg);
    }

    public static void debug(String msg, Throwable throwable) {
        initLogger().debug(msg, throwable);
    }

    public static void info(String msg) {
        initLogger().info(msg);
    }

    public static void info(String msg, Throwable throwable) {
        initLogger().info(msg, throwable);
    }

    public static void warn(String msg) {
        initLogger().warn(msg);
    }

    public static void warn(String msg, Throwable throwable) {
        initLogger().warn(msg, throwable);
    }

    public static void error(String msg) {
        initLogger().error(msg);
    }

    public static void error(String msg, Throwable throwable) {
        initLogger().error(msg, throwable);
    }

}
