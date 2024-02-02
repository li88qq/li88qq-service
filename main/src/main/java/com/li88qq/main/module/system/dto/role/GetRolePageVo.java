package com.li88qq.main.module.system.dto.role;

/**
 * @author li88qq
 * @version 1.0 2024/1/2 21:36
 */
public class GetRolePageVo {

    private Integer id;
    private String name;
    private Integer isAllMenu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsAllMenu() {
        return isAllMenu;
    }

    public void setIsAllMenu(Integer isAllMenu) {
        this.isAllMenu = isAllMenu;
    }
}
