package com.li88qq.main.dto.token;

/**
 * token
 *
 * @author li88qq
 * @version 1.0 2023/10/25 21:59
 */
public class UserToken {

    private Integer uid;//用户id
    private Long loginId;//登录记录id

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }
}
