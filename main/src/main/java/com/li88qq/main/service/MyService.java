package com.li88qq.main.service;

import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.main.dto.my.*;

/**
 * 我的
 *
 * @author li88qq
 * @version 1.0 2023/10/17 22:30
 */
public interface MyService {

    /**
     * 获取我的信息
     */
    GetMyInfoVo getInfo();

    /**
     * 获取我的个人资料
     */
    GetProfileVo getProfile();

    /**
     * 修改密码
     */
    BaseResponse updatePassword(UpdatePasswordForm form);

    /**
     * 修改手机号码
     */
    BaseResponse updateMobile(UpdateMobileForm form);

    /**
     * 修改个人资料
     */
    BaseResponse updateProfile(UpdateProfileForm form);
}
