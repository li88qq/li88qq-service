package com.li88qq.login.module.main.service;

import com.li88qq.login.module.main.dto.captcha.GetCaptchaVo;

/**
 * 验证码
 *
 * @author li88qq
 * @version 1.0 2022/1/19 23:12
 */
public interface CaptchaService {

    /**
     * 获取验证码
     */
    GetCaptchaVo getCaptcha();
}
