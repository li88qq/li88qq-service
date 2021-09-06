package com.li88qq.service.utils;

/**
 * 打星号
 *
 * @author li88qq
 * @version 1.0 2021/9/6 22:41
 */
public class MarkUtil {

    /**
     * 手机号码打星号
     *
     * @param mobile
     * @return
     */
    public static final String markMobile(String mobile) {
        if (mobile == null) {
            return "";
        }
        if (!mobile.matches("1\\d{10}")) {
            return mobile;
        }
        return String.join("", mobile.substring(0, 3), "****", mobile.substring(7));
    }

    /**
     * 身份证号码打星号
     *
     * @param idCard
     * @return
     */
    public static final String markIdCard(String idCard) {
        if (idCard == null) {
            return "";
        }
        String last = null;
        if (idCard.matches("\\d{17}(\\d|X)")) {
            last = idCard.substring(14);
        } else if (idCard.matches("\\d{15}")) {
            last = idCard.substring(12);
        } else {
            return idCard;
        }

        return String.join("", idCard.substring(0, 6), "****", last);
    }
}
