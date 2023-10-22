package com.li88qq.main.service;

import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.main.dto.login.LoginForm;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2023/10/22 17:14
 */
public interface LoginService {

    /**
     * 登录
     */
    BaseResponse login(LoginForm form);

    /**
     * 登出
     */
    BaseResponse logout();
}
