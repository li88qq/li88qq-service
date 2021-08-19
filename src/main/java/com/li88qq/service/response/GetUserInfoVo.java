package com.li88qq.service.response;

/**
 * @author li88qq
 * @version 1.0 2021/8/19 20:11
 */
public class GetUserInfoVo {

    private String nickname;//昵称
    private String photo;//头像地址

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
