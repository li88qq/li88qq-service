package com.li88qq.login.module.admin.controller;

import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.login.module.admin.dto.sms.GetAmSmsForm;
import com.li88qq.login.module.admin.service.AmSmsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 短信验证码
 *
 * @author li88qq
 * @version 1.0 2022/3/27 21:52
 */
@RestController
@RequestMapping("/am")
public class AmSmsController {

    @Resource
    private AmSmsService amSmsService;

    /**
     * 获取短信验证码
     */
    @PostMapping("/getSms")
    public BaseResponse getSms(@RequestBody @Validated GetAmSmsForm form) {
        return amSmsService.getSms(form);
    }

}
