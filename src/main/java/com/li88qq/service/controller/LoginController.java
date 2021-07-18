package com.li88qq.service.controller;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.LoginBo;
import com.li88qq.service.service.ILoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController

public class LoginController {

    @Resource
    private ILoginService loginService;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody @Valid LoginBo bo) {
        return loginService.login(bo);
    }
}