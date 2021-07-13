package com.li88qq.service.utils;

import java.util.Base64;

/**
 * 加密工具
 */
public class CodeUtil {

    /**
     * base64加密
     *
     * @param data
     * @return
     */
    public static String encode(String data) {
        if (data == null) {
            return "";
        }
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    /**
     * base64解密
     *
     * @param data
     * @return
     */
    public static String decode(String data) {
        if (data == null || data.equals("")) {
            return "";
        }
        return new String(Base64.getDecoder().decode(data));
    }


}
