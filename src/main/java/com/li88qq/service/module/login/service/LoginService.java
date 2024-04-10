package com.li88qq.service.module.login.service;

import com.li88qq.service.config.web.response.BaseResponse;
import com.li88qq.service.module.login.dto.LoginForm;
import com.li88qq.service.module.login.dto.LoginVo;

/**
 * @author li88qq
 * @version 1.0 2023/12/28 22:34
 */
public interface LoginService {

    LoginVo login(LoginForm form);

    BaseResponse logout();
}
