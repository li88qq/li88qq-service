package com.li88qq.main.dto.my;

/**
 * 我的详细信息
 *
 * @author li88qq
 * @version 1.0 2022/7/17 22:33
 */
public class DetailVo {

    private String name;//昵称
    private String mobile;//手机号码
    private String email;//邮箱
    private String head;//头像
    private Long lastLoginDate;//上次登录时间
    private String lastLoginIp;//上次登录ip
    private Long createDate;//注册时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
}
