package com.li88qq.service.constant;

/**
 * redis常量
 *
 * @author li88qq
 * @version 1.0 2021/8/25 23:11
 */
public class RedisConst {

    public static final String KEY_SESSION = "SESSION";//session key
    public static final long EXPIRE_SESSION = 2;//session有效期,单位:小时

    public static final String KEY_CAPTCHA = "CAPTCHA";//验证码key
    public static final long EXPIRE_CAPTCHA = 3;//验证码有效期,单位:分钟

    public static final String KEY_SMS = "SMS";//短信验证码
    public static final long EXPIRE_SMS = 5;//有效期,单位:分钟

    public static final String SEP = "-";

    /**
     * 构建key
     *
     * @param prefix 前缀
     * @param key    key
     * @return
     */
    public static String initKey(String prefix, String key) {
        return String.join("", SEP, prefix, SEP, key);
    }
}
