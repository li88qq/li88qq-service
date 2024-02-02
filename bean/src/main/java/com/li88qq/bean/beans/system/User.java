package com.li88qq.bean.beans.system;

import com.li88qq.bean.utils.CreateDate;
import com.li88qq.db.annotion.Id;

/**
 * 用户
 *
 * @author li88qq
 * @version 1.0 2023/12/16 11:32
 */
public class User {

    @Id
    private Integer id;//自增主键
    private String username;//用户名
    private String nickname;//昵称
    private String mobile;//手机号码
    private String email;//邮箱
    private String avatar;//头像
    private Integer roleId = 0;//角色id
    private Integer state = 0;//状态
    private String password;//密码
    private Long createDate = CreateDate.now();//创建时间
    private String createIp;//创建ip
    private Long updateDate = 0L;//更新时间
    private String updateIp;//更新ip

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }
}
