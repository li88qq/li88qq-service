package com.li88qq.service.dto;

import java.time.LocalDateTime;

/**
 * @author li88qq
 * @version 1.0 2021/8/15 17:04
 */
public class SessionCode {

    private String code;//验证码
    private LocalDateTime dateTime;//过期时间,3分钟

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
