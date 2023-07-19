package com.li88qq.common.utils;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 字符串工具类
 *
 * @author li88qq
 * @version 1.0 2021/12/17 23:05
 */
public class StringUtil {

    /**
     * 去除前后空格
     *
     * @param data 参数
     * @return 去除前后空格后的字符串
     */
    public static String trim(String data) {
        if (data == null) {
            return "";
        }
        return data.trim();
    }

    /**
     * id列表转字符串
     *
     * @param ids id数组,[1,2]
     * @return 逗号分开的字符串, 1, 2
     */
    public static String toIds(List<? extends Number> ids) {
        if (ids == null || ids.isEmpty()) {
            return "";
        }
        return ids.stream()
                .filter(item -> new BigInteger(item.toString()).compareTo(BigInteger.ZERO) > 0)
                .distinct()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    /**
     * 字符串转列表
     *
     * @param ids 字符串,1,2,3
     * @return 列表, [1, 2, 3]
     */
    public static List<Long> fromIds(String ids) {
        String value = trim(ids);
        if (value.equals("")) {
            return new ArrayList<>();
        }
        return Arrays.stream(ids.split(","))
                .filter(item -> item.matches("\\d+") && Long.parseLong(item) > 0)
                .map(Long::parseLong)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 字符串转列表
     *
     * @param ids 字符串,1,2,3
     * @return 列表, [1, 2, 3]
     */
    public static List<Integer> fromIntegerIds(String ids) {
        String value = trim(ids);
        if (value.equals("")) {
            return new ArrayList<>();
        }
        return Arrays.stream(ids.split(","))
                .filter(item -> item.matches("\\d+") && Integer.parseInt(item) > 0)
                .map(Integer::parseInt)
                .distinct()
                .collect(Collectors.toList());
    }
}
