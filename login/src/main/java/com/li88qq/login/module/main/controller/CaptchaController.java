package com.li88qq.login.module.main.controller;

import com.li88qq.login.module.main.dto.captcha.GetCaptchaVo;
import com.li88qq.login.module.main.service.CaptchaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 验证码
 *
 * @author li88qq
 * @version 1.0 2022/1/19 22:52
 */
@RestController
@RequestMapping("/p")
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    /**
     * 获取验证码
     */
    @GetMapping("/getCaptcha")
    public GetCaptchaVo getCaptcha() {
        return captchaService.getCaptcha();
    }
}
