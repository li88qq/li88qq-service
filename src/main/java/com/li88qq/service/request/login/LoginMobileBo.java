package com.li88qq.service.request.login;

import com.li88qq.service.constant.validator.annitions.Mobile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 手机短信验证码登录
 *
 * @author li88qq
 * @version 1.0 2021/10/9 21:20
 */
public class LoginMobileBo {

    @Mobile
    private String mobile;//手机号码

    @NotBlank(message = "请输入短信验证码")
    @Size(min = 6, max = 6, message = "请输入正确的短信验证码")
    private String smsCode;//短信验证码

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
