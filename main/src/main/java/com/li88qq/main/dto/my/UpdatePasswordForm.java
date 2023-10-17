package com.li88qq.main.dto.my;

/**
 * 修改密码
 *
 * @author li88qq
 * @version 1.0 2023/10/17 22:40
 */
public class UpdatePasswordForm {

    private String oldPassword;//旧密码
    private String password;//新密码

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
