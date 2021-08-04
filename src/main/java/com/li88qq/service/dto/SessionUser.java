package com.li88qq.service.dto;

/**
 * session
 */
public class SessionUser {

    private Long uid;//用户id
    private boolean visitor;//是否测试用户
    private Long loginId;//登录记录id

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public boolean isVisitor() {
        return visitor;
    }

    public void setVisitor(boolean visitor) {
        this.visitor = visitor;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }
}
