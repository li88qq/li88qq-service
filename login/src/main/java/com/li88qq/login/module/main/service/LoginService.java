package com.li88qq.login.module.main.service;

import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.login.module.main.dto.login.LoginForm;
import com.li88qq.login.module.main.dto.login.LoginVo;
import com.li88qq.login.module.main.dto.register.RegisterForm;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2022/3/21 23:18
 */
public interface LoginService {

    /**
     * 登录
     */
    LoginVo login(LoginForm form);

    /**
     * 注册
     */
    BaseResponse register(RegisterForm form);

    /**
     * 登出
     */
    BaseResponse logout();
}
