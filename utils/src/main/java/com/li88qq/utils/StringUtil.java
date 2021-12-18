package com.li88qq.utils;

import java.util.ArrayList;
import java.util.List;

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
    public static String toIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String sep = ",";
        ids.forEach(id -> {
            sb.append(id).append(sep);
        });
        sb.deleteCharAt(sb.lastIndexOf(sep));
        return sb.toString();
    }

    /**
     * 字符串转列表
     *
     * @param ids 字符串,1,2,3
     * @return 列表, [1, 2, 3]
     */
    public static List<Long> fromIds(String ids) {
        List<Long> list = new ArrayList<>();
        if (ids == null || ids.equals("")) {
            return list;
        }

        String[] arrays = ids.split(",");
        try {
            long idValue = 0L;
            for (String id : arrays) {
                idValue = Long.parseLong(id);
                if (idValue <= 0) {
                    return new ArrayList<>();
                }
                if (!list.contains(idValue)) {
                    list.add(idValue);
                }
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return list;
    }
}
