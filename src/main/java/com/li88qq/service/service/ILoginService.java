package com.li88qq.service.service;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.LoginBo;

public interface ILoginService {

    BaseResponse login(LoginBo bo);

    BaseResponse logout();
}
