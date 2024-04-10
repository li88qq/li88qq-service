package com.li88qq.service.bean.system;

/**
 * 菜单和动作关系
 *
 * @author li88qq
 * @version 1.0 2023/10/11 22:44
 */
public class Menu_Action {

    private Integer menuId;//菜单id
    private String actionId;//动作id

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }
}
