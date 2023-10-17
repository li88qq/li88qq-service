package com.li88qq.main.dto.my;

import org.springframework.web.multipart.MultipartFile;

/**
 * 修改个人资料
 *
 * @author li88qq
 * @version 1.0 2023/10/17 22:53
 */
public class UpdateProfileForm {

    private String nickname;//昵称
    private String avatar;//头像
    private MultipartFile avatarFile;//头像文件
    private String email;//邮箱

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }
}
