package com.li88qq.bean.web.session;

/**
 * 用户token信息
 *
 * @author li88qq
 * @version 1.0 2022/3/24 23:05
 */
public class UserToken {

    private String token;//token
    private Long uid;//用户id
    private Long loginDate;//登陆时间
    private Long loginId;//登录记录id

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public Long getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Long loginDate) {
        this.loginDate = loginDate;
    }
}
