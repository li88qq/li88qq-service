package com.li88qq.service.dto;

/**
 * session
 */
public class SessionUser {

    private Long uid;
    private boolean visitor;

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
}
