package com.li88qq.main.service.impl;

import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.main.dto.my.*;
import com.li88qq.main.service.MyService;
import org.springframework.stereotype.Service;

/**
 * 我的
 *
 * @author li88qq
 * @version 1.0 2023/10/17 22:58
 */
@Service
public class MyServiceImpl implements MyService {

    /**
     * 获取我的信息
     */
    @Override
    public GetMyInfoVo getInfo() {
        return null;
    }

    /**
     * 获取我的个人资料
     */
    @Override
    public GetProfileVo getProfile() {
        return null;
    }

    /**
     * 修改密码
     */
    @Override
    public BaseResponse updatePassword(UpdatePasswordForm form) {
        return null;
    }

    /**
     * 修改手机号码
     */
    @Override
    public BaseResponse updateMobile(UpdateMobileForm form) {
        return null;
    }

    /**
     * 修改个人资料
     */
    @Override
    public BaseResponse updateProfile(UpdateProfileForm form) {
        return null;
    }
}
