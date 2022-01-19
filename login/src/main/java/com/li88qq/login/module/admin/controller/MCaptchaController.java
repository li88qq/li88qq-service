package com.li88qq.login.module.admin.controller;

import com.li88qq.login.module.admin.response.GetCaptchaVo;
import com.li88qq.login.module.admin.service.IMCaptchaService;
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
@RequestMapping("/m")
public class MCaptchaController {

    @Resource
    private IMCaptchaService imCaptchaService;

    /**
     * 获取验证码
     */
    @GetMapping("/getCaptcha")
    public GetCaptchaVo getCaptcha() {
        return imCaptchaService.getCaptcha();
    }
}
