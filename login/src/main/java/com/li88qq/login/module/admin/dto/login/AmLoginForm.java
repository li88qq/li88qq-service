package com.li88qq.login.module.admin.dto.login;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2022/3/20 22:53
 */
public class AmLoginForm {

    @NotNull(message = "请输入手机号码")
    @Pattern(regexp = "^1[\\d]{10}$", message = "请输入正确的手机号码")
    private String mobile;

    @NotNull(message = "验证码不能为空")
    @Pattern(regexp = "^\\d{6}$", message = "请输入正确的短信验证码")
    private String code;//验证码

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
