package com.li88qq.service.utils;

/**
 * 类型工具
 *
 * @author li88qq
 * @version 1.0 2021/8/21 11:40
 */
public class TypeUtil {

    /**
     * 转long值
     *
     * @param object
     * @return
     */
    public static long getLong(Object object) {
        if (object == null) {
            return 0L;
        }
        try {
            return Long.parseLong(object.toString());
        } catch (Exception e) {
            return 0L;
        }
    }

    /**
     * 转int值
     *
     * @param object
     * @return
     */
    public static int getInt(Object object) {
        if (object == null) {
            return 0;
        }
        try {
            return Integer.parseInt(object.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 转String值
     *
     * @param object
     * @return
     */
    public static String getString(Object object) {
        if (object == null) {
            return "";
        }
        return object.toString();
    }
}
