package com.li88qq.service.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 创建时间
 *
 * @author li88qq
 * @version 1.0 2023/12/16 11:16
 */
public class CreateDate {

    /**
     * 获取当前时间戳
     *
     * @return 时间戳
     */
    public static long now() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }
}
