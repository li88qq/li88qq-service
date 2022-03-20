package com.li88qq.utils.encryption;

/**
 * 密码对象
 *
 * @author li88qq
 * @version 1.0 2022/3/20 22:14
 */
public class Password {

    private String password;
    private String salt;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
