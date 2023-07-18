package com.li88qq.bean.entity.utils;

/**
 * 正则工具类
 *
 * @author li88qq
 * @version 1.0 2022/4/3 23:02
 */
public class RegexUtil {

    /**
     * 简单判断是否手机号码
     *
     * @param mobile 手机号码
     * @return 是否手机号码
     */
    public static boolean isMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        return mobile.matches("1[3-9][0-9]{9}");
    }

    /**
     * 手机号码打星号
     *
     * @param mobile 手机号码
     * @return 手机号码
     */
    public static String markMobile(String mobile) {
        if (!isMobile(mobile)) {
            return mobile;
        }
        return mobile.substring(0, 3) + "****" + mobile.substring(7);
    }

}
