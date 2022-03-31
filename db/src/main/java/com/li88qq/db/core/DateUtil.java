package com.li88qq.db.core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * 日期时间工具类
 *
 * @author li88qq
 * @version 1.0 2022/3/31 22:29
 */
class DateUtil {

    /**
     * 转换成时间戳
     *
     * @param localDate 日期
     * @param localTime 时间
     * @return 时间戳
     */
    public static Long getTimeStamp(LocalDate localDate, LocalTime localTime) {
        if (localDate == null || localTime == null) {
            return null;
        }
        return localDate.atTime(localTime).toEpochSecond(ZoneOffset.of("+8"));
    }
}
