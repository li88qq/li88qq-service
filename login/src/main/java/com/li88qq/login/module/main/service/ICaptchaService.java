package com.li88qq.login.module.main.service;

import com.li88qq.login.module.main.response.GetCaptchaVo;

/**
 * 验证码
 *
 * @author li88qq
 * @version 1.0 2022/1/19 23:12
 */
public interface ICaptchaService {
    /**
     * 获取验证码
     *
     * @return
     */
    GetCaptchaVo getCaptcha();
}
