package com.li88qq.main.controller;

import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.main.dto.login.LoginForm;
import com.li88qq.main.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2023/10/22 17:13
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public BaseResponse login(LoginForm form) {
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
