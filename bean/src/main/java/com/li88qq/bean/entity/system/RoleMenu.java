package com.li88qq.bean.entity.system;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;

/**
 * 角色菜单
 *
 * @author li88qq
 * @version 1.0 2022/8/6 23:39
 */
@Table
public class RoleMenu {

    @Id
    private Integer roleId;//角色id
    @Id
    private Integer menuId; //菜单id

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
