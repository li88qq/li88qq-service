package com.li88qq.service.service;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.sms.SmsCodeBo;

/**
 * 短信服务
 *
 * @author li88qq
 * @version 1.0 2021/10/1 23:24
 */
public interface ISmsService {

    /**
     * 发送短信验证码
     *
     * @param bo
     * @return
     */
    BaseResponse sendSmsCode(SmsCodeBo bo);
}
