package com.li88qq.service.request.log;

import com.li88qq.service.dto.PageBo;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 分页查询登录记录
 *
 * @author li88qq
 * @version 1.0 2021/8/5 22:03
 */
public class GetLoginPageBo extends PageBo {

    private Integer state;
    private Integer loginType;//登录方式,1-网页,2-手机,3-扫一扫
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String loginIp;//登录ip

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

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
