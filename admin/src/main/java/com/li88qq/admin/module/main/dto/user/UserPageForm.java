package com.li88qq.admin.module.main.dto.user;

import com.li88qq.db.annotion.Like;
import com.li88qq.db.annotion.TimeMax;
import com.li88qq.db.annotion.TimeMin;
import com.li88qq.db.dto.PageForm;

import java.time.LocalDate;

/**
 * 分页查询用户
 *
 * @author li88qq
 * @version 1.0 2022/4/4 23:13
 */
public class UserPageForm extends PageForm {

    @Like
    private String username;//用户名
    @Like
    private String name;//昵称
    @Like
    private String mobile;//手机号码
    private Integer state;//状态
    @TimeMin
    private LocalDate loginDateBegin;//登录时间开始
    @TimeMax
    private LocalDate loginDateEnd;//登录时间结束
    @Like
    private String loginIp;//登录ip
    @TimeMin
    private LocalDate createDateBegin;//注册时间开始
    @TimeMax
    private LocalDate createDateEnd;//注册时间结束

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public LocalDate getLoginDateBegin() {
        return loginDateBegin;
    }

    public void setLoginDateBegin(LocalDate loginDateBegin) {
        this.loginDateBegin = loginDateBegin;
    }

    public LocalDate getLoginDateEnd() {
        return loginDateEnd;
    }

    public void setLoginDateEnd(LocalDate loginDateEnd) {
        this.loginDateEnd = loginDateEnd;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
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
}
