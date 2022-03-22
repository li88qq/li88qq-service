package com.li88qq.login.module.main.dto.captcha;

/**
 * @author li88qq
 * @version 1.0 2022/3/20 22:54
 */
public class GetCaptchaVo {

    private String code;//随机码
    private String captcha;//验证码,base64格式数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
