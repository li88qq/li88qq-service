package com.li88qq.main.dto.my;

/**
 * 获取我的信息
 *
 * @author li88qq
 * @version 1.0 2023/10/17 22:38
 */
public class GetMyInfoVo {

    private Long id;//id
    private String nickname;//昵称
    private String avatar;//头像
    private String roleName;//角色名称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
