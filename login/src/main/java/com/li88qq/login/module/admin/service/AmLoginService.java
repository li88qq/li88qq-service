package com.li88qq.login.module.admin.service;

import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.login.module.admin.dto.login.AmLoginForm;
import com.li88qq.login.module.admin.dto.login.AmLoginVo;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2022/3/21 23:18
 */
public interface AmLoginService {

    /**
     * 登录
     */
    AmLoginVo login(AmLoginForm form);

    /**
     * 登出
     */
    BaseResponse logout();
}
