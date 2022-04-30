package com.li88qq.admin.module.main.dto.log;

import com.li88qq.db.annotion.Like;
import com.li88qq.db.annotion.TimeMax;
import com.li88qq.db.annotion.TimeMin;
import com.li88qq.db.dto.PageForm;

import java.time.LocalDate;

/**
 * 登录记录
 *
 * @author li88qq
 * @version 1.0 2022/4/15 23:29
 */
public class LoginLogForm extends PageForm {

    @Like
    private String username;
    private Integer state;//登录状态
    private Integer errorCode;//异常状态码
    @TimeMin
    private LocalDate createDateBegin;//登录时间
    @TimeMax
    private LocalDate createDateEnd;//登录时间
    @Like
    private String loginIp;//登录ip

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

    public LocalDate getCreateDateBegin() {
        return createDateBegin;
    }

    public void setCreateDateBegin(LocalDate createDateBegin) {
        this.createDateBegin = createDateBegin;
    }

    public LocalDate getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(LocalDate createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
