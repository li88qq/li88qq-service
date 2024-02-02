package com.li88qq.main.module.system.dto.user;

import com.li88qq.db.dto.page.PageForm;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author li88qq
 * @version 1.0 2023/12/30 23:18
 */
public class GetUserPageForm extends PageForm {

    private String username;
    private String nickname;
    private String mobile;
    private Integer roleId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDateBegin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDateEnd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
