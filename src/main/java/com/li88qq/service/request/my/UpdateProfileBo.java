package com.li88qq.service.request.my;

/**
 * 修改个人信息
 */
public class UpdateProfileBo {

    private String nickname;//昵称
    private String email;//邮箱
    private String mobile;//手机号码

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
