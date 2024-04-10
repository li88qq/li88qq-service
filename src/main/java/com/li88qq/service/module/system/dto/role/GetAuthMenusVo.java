package com.li88qq.service.module.system.dto.role;

import java.util.List;

/**
 * @author li88qq
 * @version 1.0 2024/1/4 22:48
 */
public class GetAuthMenusVo {

    private Integer isAllMenu;
    private List<Integer> menuIds;

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
