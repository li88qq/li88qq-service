package com.li88qq.service.response;

/**
 * 分页查询登录记录
 *
 * @author li88qq
 * @version 1.0 2021/8/5 22:03
 */
public class GetLoginPageVo {

    private Long id;
    private Integer state;//登录状态,1-正常,2-失败
    private Integer loginType;//登录方式,1-网页,2-手机,3-扫一扫
    private Long createDate;//登录时间
    private String loginIp;//登录ip
    private Long updateDate;//登出时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
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

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
