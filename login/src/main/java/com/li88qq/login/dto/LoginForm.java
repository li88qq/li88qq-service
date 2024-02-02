package com.li88qq.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author li88qq
 * @version 1.0 2023/12/28 22:31
 */
public class LoginForm {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 32, message = "用户名格式错误")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 32, message = "密码格式错误")
    private String password;

//    @NotBlank(message = "验证码不能为空")
//    @Size(min = 4, max = 4, message = "验证码格式错误")
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
