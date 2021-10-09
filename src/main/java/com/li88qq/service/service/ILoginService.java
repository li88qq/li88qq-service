package com.li88qq.service.service;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.login.LoginBo;
import com.li88qq.service.request.login.LoginMobileBo;

public interface ILoginService {

    /**
     * 登录
     *
     * @param bo
     * @return
     */
    BaseResponse login(LoginBo bo);

    /**
     * 登出
     *
     * @return
     */
    BaseResponse logout();

    /**
     * 手机号码登录
     *
     * @param bo
     * @return
     */
    BaseResponse loginMobile(LoginMobileBo bo);
}
