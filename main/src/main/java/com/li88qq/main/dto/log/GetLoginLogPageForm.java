package com.li88qq.main.dto.log;

import com.li88qq.db.dto.page.PageForm;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 登录记录
 *
 * @author li88qq
 * @version 1.0 2023/10/17 23:00
 */
public class GetLoginLogPageForm extends PageForm {

    private Integer state;//登录状态
    private String ip;//登录ip
    private Integer errorState;//异常状态

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getErrorState() {
        return errorState;
    }

    public void setErrorState(Integer errorState) {
        this.errorState = errorState;
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
