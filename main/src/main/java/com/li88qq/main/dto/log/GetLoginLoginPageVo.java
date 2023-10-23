package com.li88qq.main.dto.log;

/**
 * 登录记录
 *
 * @author li88qq
 * @version 1.0 2023/10/17 23:01
 */
public class GetLoginLoginPageVo {

    private Long id;//自增主键
    private Long createDate;//登录时间
    private String ip;//登录ip
    private Integer state;//登录状态
    private Integer errorState;//异常状态
    private Long logoutDate;//登出时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
