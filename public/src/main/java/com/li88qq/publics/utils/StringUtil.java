package com.li88qq.publics.utils;

/**
 * String工具类
 *
 * @author li88qq
 * @version 1.0 2023/12/16 16:27
 */
public class StringUtil {

    /**
     * 去掉前后空格
     *
     * @param value 字符串
     * @return 去掉前后空格的字符串
     */
    public static String trim(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    /**
     * 校验非空
     *
     * @param value 字符串
     * @return 是否非空
     */
    public static boolean notBlank(String value) {
        return !(value == null || value.trim().isEmpty());
    }

}
