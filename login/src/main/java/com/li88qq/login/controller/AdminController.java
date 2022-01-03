package com.li88qq.login.controller;

import com.li88qq.utils.response.BaseResponse;
import com.li88qq.utils.response.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理后台登录
 *
 * @author li88qq
 * @version 1.0 2022/1/3 23:08
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    /**
     * 登录
     */
    @PostMapping("/login")
    public BaseResponse login() {
        return ResponseUtil.ok();
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public BaseResponse logout() {
        return ResponseUtil.ok();
    }

}
