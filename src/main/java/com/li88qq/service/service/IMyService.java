package com.li88qq.service.service;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.my.UpdatePasswordBo;
import com.li88qq.service.request.my.UpdateProfileBo;
import com.li88qq.service.response.GetProfileVo;
import com.li88qq.service.response.GetUserInfoVo;

public interface IMyService {

    BaseResponse updatePassword(UpdatePasswordBo bo);

    BaseResponse updateProfile(UpdateProfileBo bo);

    GetProfileVo getProfile();

    GetUserInfoVo getInfo();
}
