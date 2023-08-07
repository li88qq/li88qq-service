package com.li88qq.login.admin.service;

import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.login.admin.dto.login.LoginForm;

/**
 * 后台登录
 *
 * @author li88qq
 * @version 1.0 2023/8/7 22:39
 */
public interface AdminLoginService {

    /**
     * 登录
     */
    BaseResponse login(LoginForm form);
}
