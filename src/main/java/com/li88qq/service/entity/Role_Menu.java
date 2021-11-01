package com.li88qq.service.entity;


import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 权限菜单关联
 *
 * @author li88qq
 * @version 1.0 2021/11/1 23:17
 */
@Table("Role_Menu")
public class Role_Menu {

    @Id
    private Long roleId;//角色id
    private Long menuId;//菜单id

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
