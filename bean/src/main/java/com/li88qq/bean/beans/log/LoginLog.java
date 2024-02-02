package com.li88qq.bean.beans.log;

import com.li88qq.bean.utils.CreateDate;
import com.li88qq.db.annotion.Id;

/**
 * 登录记录
 *
 * @author li88qq
 * @version 1.0 2023/10/11 22:40
 */
public class LoginLog {

    @Id
    private Long id;//自增主键
    private Integer uid = 0;//用户id
    private Long createDate = CreateDate.now();//登录时间
    private String ip;//登录ip
    private Integer state = 0;//登录状态
    private Integer errorState = 0;//异常状态
    private Long logoutDate = 0L;//登出时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getErrorState() {
        return errorState;
    }

    public void setErrorState(Integer errorState) {
        this.errorState = errorState;
    }

    public Long getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Long logoutDate) {
        this.logoutDate = logoutDate;
    }
}
