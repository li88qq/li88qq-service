package com.li88qq.bean.entity.main.user;

import com.li88qq.db.annotion.Id;

/**
 * 角色和菜单关系
 *
 * @author li88qq
 * @version 1.0 2023/10/11 22:42
 */
public class RoleMenu {

    @Id
    private Integer roleId; //角色id
    @Id
    private Integer menuId;//菜单id

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}
