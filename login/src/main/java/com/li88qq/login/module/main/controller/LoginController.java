package com.li88qq.login.module.main.controller;

import com.li88qq.login.module.main.dto.login.LoginForm;
import com.li88qq.login.module.main.dto.login.LoginVo;
import com.li88qq.login.module.main.dto.register.RegisterForm;
import com.li88qq.login.module.main.service.LoginService;
import com.li88qq.utils.response.BaseResponse;
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
@RequestMapping("/p")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public LoginVo login(@RequestBody LoginForm form) {
        return loginService.login(form);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public BaseResponse register(@RequestBody RegisterForm form) {
        return loginService.register(form);
    }
}