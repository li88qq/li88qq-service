package com.li88qq.login.module.main.dto.register;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 注册
 *
 * @author li88qq
 * @version 1.0 2022/3/22 21:21
 */
public class RegisterForm {

    @NotNull(message = "用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名长度为(4,20)字符")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度为(6,20)字符")
    private String password;

    @Pattern(regexp = "^$|^1[\\d]{10}$", message = "请输入正确的手机号码")
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
