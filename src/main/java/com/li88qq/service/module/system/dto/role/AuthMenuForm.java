package com.li88qq.service.module.system.dto.role;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色授权菜单
 *
 * @author li88qq
 * @version 1.0 2024/1/4 21:43
 */
public class AuthMenuForm {

    @NotNull
    @Min(value = 1)
    private Integer roleId;
    private Integer isAllMenu;
    private List<Integer> menuIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getIsAllMenu() {
        return isAllMenu;
    }

    public void setIsAllMenu(Integer isAllMenu) {
        this.isAllMenu = isAllMenu;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }
}
