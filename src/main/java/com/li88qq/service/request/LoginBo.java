package com.li88qq.service.request;

import com.li88qq.service.constant.annitions.Password;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginBo {

    @NotBlank(message = "请输入用户名")
    @Size(min = 2, max = 12, message = "用户名长度2-12字符")
    private String username;
    @Password
    private String password;
    @NotBlank(message = "请输入验证码")
    @Size(min = 4, max = 4, message = "请输入正确的验证码")
    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
