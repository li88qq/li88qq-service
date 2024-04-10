package com.li88qq.service.module.system.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author li88qq
 * @version 1.0 2023/12/30 23:11
 */
public class SaveUserForm {

    @NotBlank
    @Size(min = 4,max = 32)
    private String username;

    @NotBlank
    @Size(min = 6,max = 32)
    private String password;

    private String nickname;
    private String mobile;

    private Integer roleId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
