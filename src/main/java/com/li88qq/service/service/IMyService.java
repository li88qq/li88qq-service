package com.li88qq.service.service;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.my.UpdatePasswordBo;

public interface IMyService {

    BaseResponse updatePassword(UpdatePasswordBo bo);
}
