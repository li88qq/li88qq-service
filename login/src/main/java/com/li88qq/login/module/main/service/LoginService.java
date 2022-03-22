package com.li88qq.login.module.main.service;

import com.li88qq.login.module.main.dto.login.LoginForm;
import com.li88qq.login.module.main.dto.login.LoginVo;
import com.li88qq.login.module.main.dto.register.RegisterForm;
import com.li88qq.utils.response.BaseResponse;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2022/3/21 23:18
 */
public interface LoginService {

    LoginVo login(LoginForm form);

    BaseResponse register(RegisterForm form);
}
