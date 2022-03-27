package com.li88qq.login.module.admin.controller;

import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.login.module.admin.dto.login.AmLoginForm;
import com.li88qq.login.module.admin.dto.login.AmLoginVo;
import com.li88qq.login.module.admin.service.AmLoginService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2022/1/24 23:24
 */
@RestController
@RequestMapping("/am")
public class AmLoginController {

    @Resource
    private AmLoginService amLoginService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public AmLoginVo login(@RequestBody @Validated AmLoginForm form) {
        return amLoginService.login(form);
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public BaseResponse logout() {
        return amLoginService.logout();
    }
}
