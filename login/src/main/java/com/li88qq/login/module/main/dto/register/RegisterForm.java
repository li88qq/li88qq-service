package com.li88qq.login.module.main.dto.register;

/**
 * 注册
 *
 * @author li88qq
 * @version 1.0 2022/3/22 21:21
 */
public class RegisterForm {

    private String username;
    private String password;
    private String mobile;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
