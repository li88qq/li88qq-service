package com.li88qq.login.admin.service.impl;

import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.login.admin.dto.login.LoginForm;
import com.li88qq.login.admin.service.AdminLoginService;
import org.springframework.stereotype.Service;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2023/8/7 22:40
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    /**
     * 登录
     */
    @Override
    public BaseResponse login(LoginForm form) {
        //校验验证码
        //校验账号密码
        //登录后处理
        //保存登录记录
        return null;
    }
}
