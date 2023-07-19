package com.li88qq.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5工具类
 *
 * @author li88qq
 * @version 1.0 2022/7/26 23:18
 */
public class MD5Util {

    /**
     * 获取md5
     *
     * @param data 数据
     * @return md5
     */
    public static String md5(String data) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = null;
            mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(data.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            return byteToHex(md);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 数组转字符串
     *
     * @param bytes 数组
     * @return 字符串
     */
    public static String byteToHex(byte[] bytes) {
        // 把密文转换成十六进制的字符串形式
        // 可以随意地填,16个字符即可, 不可按顺序 0-F 填写.
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int j = bytes.length;
        char[] str = new char[j * 2];
        int k = 0;
        byte byte0;
        for (int i = 0; i < j; i++) {
            byte0 = bytes[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str).toLowerCase();
    }
}
