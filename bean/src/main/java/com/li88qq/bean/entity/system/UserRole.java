package com.li88qq.bean.entity.system;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;

/**
 * 用户角色
 *
 * @author li88qq
 * @version 1.0 2022/8/6 23:38
 */
@Table
public class UserRole {

    @Id
    private Long uid;//用户id
    @Id
    private Integer roleId;//角色id

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
