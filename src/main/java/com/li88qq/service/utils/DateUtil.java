package com.li88qq.service.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 日期工具类
 *
 * @author li88qq
 * @version 1.0 2023/12/16 16:27
 */
public class DateUtil {

    /**
     * 获取当前时间戳
     *
     * @return 时间戳
     */
    public static long getTimestamp() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }
}
