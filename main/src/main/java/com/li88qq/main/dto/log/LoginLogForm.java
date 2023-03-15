package com.li88qq.main.dto.log;

import com.li88qq.db.dto.page.PageForm;

import java.time.LocalDate;

/**
 * 登录记录
 *
 * @author li88qq
 * @version 1.0 2022/9/8 23:26
 */
public class LoginLogForm extends PageForm {

    private Integer state;
    private Integer errorCode;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String loginIp;

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

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
