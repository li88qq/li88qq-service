package com.li88qq.login.service;

import com.li88qq.login.dto.LoginForm;
import com.li88qq.login.dto.LoginVo;
import com.li88qq.publics.web.response.BaseResponse;

/**
 * @author li88qq
 * @version 1.0 2023/12/28 22:34
 */
public interface LoginService {

    LoginVo login(LoginForm form);

    BaseResponse logout();
}
