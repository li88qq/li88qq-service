package com.li88qq.service.entity;

import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 用户角色关系
 *
 * @author li88qq
 * @version 1.0 2021/11/1 23:20
 */
@Table("User_Role")
public class User_Role {

    @Id
    private Long uid;//用户id
    private Long roleId;//角色id

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
