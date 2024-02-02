package com.li88qq.main.module.my.dto.my;

/**
 * @author li88qq
 * @version 1.0 2023/12/29 23:00
 */
public class GetMyInfoVo {

    private Integer userId;
    private String username;
    private String nickname;
    private String realName;
    private String avatar;//头像

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
