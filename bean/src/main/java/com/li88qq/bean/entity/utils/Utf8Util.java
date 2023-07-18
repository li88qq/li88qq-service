package com.li88qq.bean.entity.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * utf8工具类
 *
 * @author li88qq
 * @version 1.0 2021/9/4 23:28
 */
public class Utf8Util {

    /**
     * 获取所有的utf8汉字
     *
     * @return 返回0X4E00~0x9fa5汉字
     */
    public static List<Map<String, Object>> getUtf8Words() {
        int begin = 0X4E00;
        int end = 0x9fa5;
        List<Map<String, Object>> result = new ArrayList<>();
        String hex = null;
        String character = null;
        Map<String, Object> map = null;
        for (int i = begin; i <= end; i++) {
            hex = Integer.toHexString(i);
            character = String.valueOf((char) i);

            map = new HashMap<>();
            map.put("hex", hex);
            map.put("character", character);
            result.add(map);
        }
        return result;
    }

    /**
     * 字符转16进制
     *
     * @param c 字符
     * @return 16进制字符
     */
    public static String charToHex(char c) {
        return Integer.toHexString(c);
    }

    /**
     * 获取所有utf8汉字
     *
     * @return 字符集
     */
    public static String getWords() {
        int begin = 0X4E00;
        int end = 0x9fa5;
        String character = null;
        StringBuilder sb = new StringBuilder();
        for (int i = begin; i <= end; i++) {
            character = String.valueOf((char) i);

            sb.append(character);
        }
        return sb.toString();
    }
}