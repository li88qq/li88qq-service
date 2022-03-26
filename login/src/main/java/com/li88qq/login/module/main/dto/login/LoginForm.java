package com.li88qq.login.module.main.dto.login;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2022/3/20 22:53
 */
public class LoginForm {

    @NotNull(message = "用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名长度为(4,20)字符")
    private String username;//用户名

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度为(6,20)字符")
    private String password;//密码

    @NotNull(message = "验证码不能为空")
    @Size(min = 4, max = 4, message = "验证码长度为(4)字符")
    private String code;//验证码

    @NotNull(message = "请重新获取验证码")
    @Size(min = 8, max = 8, message = "请重新获取验证码")
    private String checkCode;//验证码随机码

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

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
