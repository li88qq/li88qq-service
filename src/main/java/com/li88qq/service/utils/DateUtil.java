package com.li88qq.service.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 获取当前时间戳(秒)
     *
     * @return
     */
    public static long getTimestamp() {
        return getTimestamp(LocalDateTime.now());
    }

    public static long getTimestamp(LocalDate localDate) {
        if (localDate == null) {
            return 0L;
        }
        return getTimestamp(localDate.atTime(LocalTime.MIN));
    }

    public static long getTimestamp(LocalDateTime dateTime) {
        if (dateTime == null) {
            return 0L;
        }
        return dateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 时间格式化
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            dateTime = LocalDateTime.now();
        }
        if (pattern == null || pattern.equals("")) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            String result = DateTimeFormatter.ofPattern(pattern).format(dateTime);
            return result;
        } catch (Exception e) {
            return "";
        }
    }

}
