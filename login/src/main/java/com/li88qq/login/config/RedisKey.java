package com.li88qq.login.config;

import java.util.concurrent.TimeUnit;

/**
 * redis key管理
 *
 * @author li88qq
 * @version 1.0 2022/3/20 23:18
 */
public enum RedisKey {

    /***************************前台:P ************************/
    P_CAPTCHA("p:captcha:", 3, TimeUnit.MINUTES),//验证码
    P_SMS_CODE("p:sms_code:", 15, TimeUnit.MINUTES),//短信验证码
    P_TOKEN("p:token", 60, TimeUnit.MINUTES),//token


    /***************************后台:AM************************/
    AM_CAPTCHA("am:captcha:", 3, TimeUnit.MINUTES),//验证码
    AM_SMS_CODE("am:sms_code:", 15, TimeUnit.MINUTES),//短信验证码
    AM_TOKEN("am:token", 60, TimeUnit.MINUTES),//token

    ;


    private String key;//key
    private int time;//时间
    private TimeUnit timeUnit;//时间单位

    RedisKey(String key, int time, TimeUnit timeUnit) {
        this.key = key;
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}