package com.li88qq.login.module.main.dto.login;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2022/3/20 22:53
 */
public class LoginForm {

    private String username;//用户名
    private String password;//密码
    private String code;//验证码
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
