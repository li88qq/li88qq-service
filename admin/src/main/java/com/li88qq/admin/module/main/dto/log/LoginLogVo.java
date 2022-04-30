package com.li88qq.admin.module.main.dto.log;

/**
 * @author li88qq
 * @version 1.0 2022/4/15 23:33
 */
public class LoginLogVo {

    private Long id;//自增长主键
    private Long uid;//人员id
    private String username;
    private Integer state;//登录状态
    private Integer errorCode;//异常状态码
    private Long createDate;//登录时间
    private String loginIp;//登录ip
    private Long logoutDate;//登出时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
