package com.li88qq.login.module.admin.dto.sms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 获取短信验证码
 *
 * @author li88qq
 * @version 1.0 2022/3/27 21:06
 */
public class GetAmSmsForm {

    @NotNull(message = "请输入手机号码")
    @Pattern(regexp = "^1[\\d]{10}$", message = "请输入正确的手机号码")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
