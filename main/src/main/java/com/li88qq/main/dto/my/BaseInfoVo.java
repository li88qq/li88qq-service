package com.li88qq.main.dto.my;

/**
 * 个人基本信息
 *
 * @author li88qq
 * @version 1.0 2022/7/17 22:27
 */
public class BaseInfoVo {

    private String name;//昵称
    private String head;//头像
    private Long lastLoginDate;//上次登录时间
    private String lastLoginIp;//上次登录ip

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
