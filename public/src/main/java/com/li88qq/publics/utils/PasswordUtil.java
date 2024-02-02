package com.li88qq.publics.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码
 *
 * @author li88qq
 * @version 1.0 2024/1/13 14:47
 */
public class PasswordUtil {

    /**
     * 获取加密后的密码
     *
     * @param password 密码
     * @return 加密后密码
     */
    public static String getPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B);
        return encoder.encode(password);
    }

    /**
     * 校验密码
     *
     * @param password     校验密码
     * @param passwordHash 加密后的密码
     * @return 密码是否正确
     */
    public static boolean checkPassword(String password, String passwordHash) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B);
        return encoder.matches(password, passwordHash);
    }

    public static void main(String[] args) {
        String password = "123456";
        String passwordHash = PasswordUtil.getPassword(password);
        boolean isCheck = PasswordUtil.checkPassword(password, passwordHash);
        System.out.println(passwordHash);
        System.out.println(isCheck);
    }
}
