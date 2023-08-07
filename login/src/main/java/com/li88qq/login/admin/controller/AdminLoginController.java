package com.li88qq.login.admin.controller;

import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.login.admin.dto.login.LoginForm;
import com.li88qq.login.admin.service.AdminLoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台登录
 * @author li88qq
 * @version 1.0 2023/7/31 23:41
 */
@RestController
@RequestMapping("/m")
public class AdminLoginController {

    @Resource
    private AdminLoginService adminLoginService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public BaseResponse login(LoginForm form){
        return adminLoginService.login(form);
    }

}
