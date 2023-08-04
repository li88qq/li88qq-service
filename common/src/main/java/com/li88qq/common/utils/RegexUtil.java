package com.li88qq.common.utils;

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

    /**
     * 校验是否数字
     *
     * <p>0</p>
     * <p>整数,首位数字不为0</p>
     * <p>小数,包含.数字,如果首位数字为0,则后面紧跟.</p>
     *
     * @param value 字符串
     * @return 是否数字
     */
    public static boolean isNumber(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        //格式 0,整数,小数
        String regex = "^(0)|(-?[1-9][0-9]?)|(-?(0|[1-9][0-9]?)\\.[0-9]+)$";
        return value.matches(regex);
    }

}
