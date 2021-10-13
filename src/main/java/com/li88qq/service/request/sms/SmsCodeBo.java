package com.li88qq.service.request.sms;

import com.li88qq.service.constant.validator.annitions.Mobile;

/**
 * 发送短信验证码
 *
 * @author li88qq
 * @version 1.0 2021/10/1 23:43
 */
public class SmsCodeBo {

    private Integer type;//操作类型
    @Mobile
    private String mobile;//手机号码

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
