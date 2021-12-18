package com.li88qq.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * 日期工具类
 *
 * @author li88qq
 * @version 1.0 2021/12/17 23:13
 */
public class DateUtil {

    /**
     * 获取当前时间戳
     *
     * @return 时间暗藏
     */
    public static long getTimestamp() {
        return getTimestamp(LocalDateTime.now());
    }

    /**
     * 获取当天最早时间戳
     *
     * @param localDate 日期
     * @return 当天最早时间戳
     */
    public static long getMinTimestamp(LocalDate localDate) {
        if (localDate == null) {
            return 0L;
        }
        return getTimestamp(localDate.atTime(LocalTime.MIN));
    }

    /**
     * 获取当天最晚时间戳
     *
     * @param localDate 日期
     * @return 当天最晚时间戳
     */
    public static long getMaxTimestamp(LocalDate localDate) {
        if (localDate == null) {
            return 0L;
        }
        return getTimestamp(localDate.atTime(LocalTime.MAX));
    }


    /**
     * 获取时间戳
     *
     * @param localDateTime 时间
     * @return 时间戳
     */
    public static long getTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return 0L;
        }
        return localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }
}
