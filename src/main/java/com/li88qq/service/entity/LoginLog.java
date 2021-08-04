package com.li88qq.service.entity;

import com.li88qq.service.utils.DateUtil;
import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 登录记录
 */
@Table("LoginLog")
public class LoginLog {

    @Id
    private Long id;//id
    private Long uid = 0L;//用户id
    private Integer state = 0;//登录状态,1-正常,2-失败
    private String remark;//详情
    private Integer loginType = 0;//登录方式,1-网页,2-手机,3-扫一扫
    private Long createDate = DateUtil.getTimestamp();//登录时间
    private String loginIp;//登录ip
    private Long updateDate = 0L;//登出时间

    public LoginLog() {
    }

    public LoginLog(Long uid, Integer state, String remark, Integer loginType, String loginIp) {
        this.uid = uid;
        this.state = state;
        this.remark = remark;
        this.loginType = loginType;
        this.loginIp = loginIp;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }
}
