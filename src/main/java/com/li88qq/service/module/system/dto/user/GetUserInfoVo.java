package com.li88qq.service.module.system.dto.user;

/**
 * @author li88qq
 * @version 1.0 2024/1/1 22:16
 */
public class GetUserInfoVo {

    private String username;
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
}
