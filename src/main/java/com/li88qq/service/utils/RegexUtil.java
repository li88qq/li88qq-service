package com.li88qq.service.utils;

/**
 * 正则表达式
 */
public class RegexUtil {

    /**
     * 手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        //简单校验 11位数,首位1
        String regex = "1\\d{10}";
        return mobile != null && mobile.matches(regex);
    }

    /**
     * 是否邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        //2位及以上字符@2位及以上字符.2位及以上字符
        String regex = "\\w{2,}@(\\w{2,}\\.)+\\w{2,}";
        return email != null && email.matches(regex);
    }
}
