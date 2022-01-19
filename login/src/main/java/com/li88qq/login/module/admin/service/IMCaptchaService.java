package com.li88qq.login.module.admin.service;

import com.li88qq.login.module.admin.response.GetCaptchaVo;

/**
 * 验证码
 *
 * @author li88qq
 * @version 1.0 2022/1/19 23:12
 */
public interface IMCaptchaService {
    /**
     * 获取验证码
     *
     * @return
     */
    GetCaptchaVo getCaptcha();
}
