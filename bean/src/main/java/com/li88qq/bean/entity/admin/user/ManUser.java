package com.li88qq.bean.entity.admin.user;

import com.li88qq.bean.entity.utils.CreateDate;
import com.li88qq.db.annotion.Id;

/**
 * 后台用户
 *
 * @author li88qq
 * @version 1.0 2023/7/31 23:08
 */
public class ManUser {

    @Id
    private Integer id;//自增主键
    private String username;//用户名
    private String nickname;//昵称
    private String avatar;//头像
    private String mobile;//手机号码
    private String email;//邮箱
    private Integer state = 0;//状态

    private String password;//密码
    private String randomCode;//随机码

    private Long createDate = CreateDate.now();//创建时间
    private String createIp;//创建ip

    private Long updateDate = 0L;//更新时间

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
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
}
