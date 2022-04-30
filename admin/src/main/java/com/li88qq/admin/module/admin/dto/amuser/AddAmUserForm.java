package com.li88qq.admin.module.admin.dto.amuser;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 添加用户(后台)
 *
 * @author li88qq
 * @version 1.0 2022/4/1 23:15
 */
public class AddAmUserForm {

    @NotNull(message = "请输入用户名")
    @Pattern(regexp = "^[a-z][a-z0-9.@]{3,15}$", message = "用户名格式错误")
    private String username;
    @NotNull(message = "请输入手机号码")
    @Pattern(regexp = "^1[\\d]{10}$", message = "请输入正确的手机号码")
    private String mobile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
