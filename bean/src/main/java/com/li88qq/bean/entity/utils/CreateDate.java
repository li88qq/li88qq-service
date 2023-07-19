package com.li88qq.bean.entity.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 创建时间
 *
 * @author li88qq
 * @version 1.0 2023/7/19 22:48
 */
public class CreateDate {

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳
     */
    public static Long now() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }
}
