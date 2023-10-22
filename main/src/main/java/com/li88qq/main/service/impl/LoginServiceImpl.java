package com.li88qq.main.service.impl;

import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.main.dto.login.LoginForm;
import com.li88qq.main.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2023/10/22 17:16
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * 登录
     */
    @Override
    public BaseResponse login(LoginForm form) {
        return null;
    }

    /**
     * 登出
     */
    @Override
    public BaseResponse logout() {
        return null;
    }
}
