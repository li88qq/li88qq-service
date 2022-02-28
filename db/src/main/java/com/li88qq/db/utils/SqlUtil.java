package com.li88qq.db.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author li88qq
 * @version 1.0 2022/2/28 23:11
 */
public class SqlUtil {

    /**
     * 去掉前后空格
     *
     * @param value 值
     * @return 去除前后空格
     */
    public static String trim(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    /**
     * 取默认值
     *
     * @param value        值
     * @param defaultValue 默认值
     * @return 如果值不为空, 返回值, 否则返回默认值
     */
    public static String option(String value, String defaultValue) {
        value = trim(value);
        if (!value.equals("")) {
            return value;
        }
        return defaultValue;
    }

    /**
     * 查询符合正则的所有字符
     *
     * @param value 字符串
     * @param regex 正常
     * @return 符合正则的字符串列表
     */
    public static String[] matchesParam(String value, String regex) {
        if (value == null || value.equals("")) {
            return null;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        List<String> values = new ArrayList<>();
        while (matcher.find()) {
            values.add(matcher.group());
        }
        if (!values.isEmpty()) {
            return values.toArray(new String[0]);
        }
        return null;
    }

}
