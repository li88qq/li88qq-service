package com.li88qq.service.controller;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.login.LoginBo;
import com.li88qq.service.request.login.LoginMobileBo;
import com.li88qq.service.service.ILoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 登录管理
 */
@RestController
public class LoginController {

    @Resource
    private ILoginService loginService;

    /**
     * 登录
     *
     * @param bo
     * @return
     */
    @PostMapping("/login")
    public BaseResponse login(@RequestBody @Valid LoginBo bo) {
        return loginService.login(bo);
    }

    /**
     * 登出
     *
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse logout() {
        return loginService.logout();
    }

    /**
     * 登录
     *
     * @param bo
     * @return
     */
    @PostMapping("/loginMobile")
    public BaseResponse loginMobile(@RequestBody @Valid LoginMobileBo bo) {
        return loginService.loginMobile(bo);
    }
}
