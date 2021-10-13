package com.li88qq.service.request.my;

import com.li88qq.service.constant.validator.annitions.Password;

import javax.validation.constraints.NotBlank;

public class UpdatePasswordBo {

    @Password
    @NotBlank(message = "请输入旧密码")
    private String oldPassword;
    @Password
    @NotBlank(message = "请输入密码")
    private String password;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
