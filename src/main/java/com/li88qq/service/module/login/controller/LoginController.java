package com.li88qq.service.module.login.controller;

import com.li88qq.service.config.web.response.BaseResponse;
import com.li88qq.service.module.login.dto.LoginForm;
import com.li88qq.service.module.login.dto.LoginVo;
import com.li88qq.service.module.login.service.LoginService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author li88qq
 * @version 1.0 2023/12/27 22:54
 */
@RestController
@Validated
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public LoginVo login(@RequestBody @Valid LoginForm form) {
        return loginService.login(form);
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public BaseResponse logout() {
        return loginService.logout();
    }
}
