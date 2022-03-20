package com.li88qq.db.core;

import java.lang.reflect.Array;

/**
 * 字符串工具类
 *
 * @author li88qq
 * @version 1.0 2022/3/8 21:36
 */
class StringUtil {

    /**
     * 重复字符串
     *
     * @param value 值
     * @param count 重复次数
     * @param sep   分隔符
     * @return 重复字符串
     */
    public static String repeat(String value, int count, String sep) {
        StringBuilder sb = new StringBuilder();

        if (sep == null || sep.equals("")) {
            return value.repeat(count);
        }

        for (int i = 0; i < count; i++) {
            sb.append(value).append(sep);
        }
        sb.deleteCharAt(sb.lastIndexOf(sep));
        return sb.toString();
    }

    /**
     * 重复字符串两次
     *
     * @param count  重复次数
     * @param count2 第二轮重复次数
     * @return 重复字符串
     */
    public static String repeatTwo(int count, int count2) {
        StringBuilder sb = new StringBuilder();

        String left = "(";
        String right = ")";
        String place = "?";
        String sep = ",";
        for (int i = 0; i < count2; i++) {
            sb.append(left);
            for (int j = 0; j < count; j++) {
                sb.append(place).append(sep);
            }
            sb.deleteCharAt(sb.lastIndexOf(sep));
            sb.append(right);
            sb.append(sep);
        }

        sb.deleteCharAt(sb.lastIndexOf(sep));
        return sb.toString();
    }

    /**
     * 去除前后空格
     *
     * @param value 值
     * @return 值
     */
    public static String trim(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    /**
     * 两个数组合并
     *
     * @param arr1   数组1
     * @param arr2   数组2
     * @param tClass 泛型类型
     * @return 合并后的数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] join(T[] arr1, T[] arr2, Class<T> tClass) {
        T[] array = (T[]) Array.newInstance(tClass, arr1.length + arr2.length);
        int index = 0;
        for (T t : arr1) {
            array[index++] = t;
        }
        for (T t : arr2) {
            array[index++] = t;
        }
        return array;
    }

    /**
     * kv
     *
     * @param arr 数组
     * @return k1=?,k2=?
     */
    public static String kv(String[] arr) {
        StringBuilder sb = new StringBuilder();
        String value = "=?";
        String sep = ",";
        for (String key : arr) {
            sb.append(key).append(value).append(sep);
        }
        sb.deleteCharAt(sb.lastIndexOf(sep));
        return sb.toString();
    }

    /**
     * kvId
     *
     * @param arr 数组
     * @return k1=? and k2=?
     */
    public static String kvId(String[] arr) {
        StringBuilder sb = new StringBuilder();
        String value = "=?";
        String sep = " and ";
        sb.append(arr[0]).append(value);
        for (int i = 1; i < arr.length; i++) {
            sb.append(sep).append(arr[i]).append(value);
        }

        return sb.toString();
    }

}
