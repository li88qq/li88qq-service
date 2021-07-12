package com.li88qq.service.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    /**
     * 去除前后空格
     *
     * @param value
     * @return
     */
    public static String trim(String value) {
        if (value == null) {
            return "";
        }
        return value.trim();
    }

    /**
     * like处理
     *
     * @param value
     * @return
     */
    public static String like(String value) {
        value = trim(value);
        if (value.equals("")) {
            return null;
        }
        return String.join("", "%", value, "%");
    }

    /**
     * id字符串转列表
     *
     * @param ids
     * @return
     */
    public static List<Long> toIds(String ids) {
        List<Long> idList = new ArrayList<>();
        ids = trim(ids);
        if (ids.equals("")) {
            return idList;
        }
        try {
            for (String id : ids.split(",")) {
                idList.add(Long.valueOf(id));
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return idList;
    }

    /**
     * id列表转字符串
     *
     * @param idList
     * @return
     */
    public static String fromIds(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        idList.forEach(a -> sb.append(a).append(","));
        String ids = sb.toString();
        return ids.substring(0, ids.length() - 1);
    }
}
