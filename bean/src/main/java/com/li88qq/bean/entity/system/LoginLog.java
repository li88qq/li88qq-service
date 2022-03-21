package com.li88qq.bean.entity.system;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;
import com.li88qq.utils.DateUtil;

/**
 * 登录记录
 *
 * @author li88qq
 * @version 1.0 2022/3/21 23:30
 */
@Table("LoginLog")
public class LoginLog {

    @Id
    private Long id;//自增长主键
    private Long uid = 0L;//用户id
    private Integer state = 0;//登录状态
    private Integer errorCode = 0;//异常状态码
    private Long createDate = DateUtil.getTimestamp();//登录时间
    private String loginIp;//登录ip
    private Long logoutDate = 0L;//登出时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Long getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Long logoutDate) {
        this.logoutDate = logoutDate;
    }
}
