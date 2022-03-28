package com.li88qq.login.module.admin.service;

import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.login.module.admin.dto.sms.GetAmSmsForm;

/**
 * 短信验证码
 *
 * @author li88qq
 * @version 1.0 2022/3/27 21:54
 */
public interface AmSmsService {

    /**
     * 获取短信验证码
     */
    BaseResponse getSms(GetAmSmsForm form);
}
