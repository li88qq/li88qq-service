package com.li88qq.main.module.system.dto.role;

import com.li88qq.db.dto.page.PageForm;

/**
 * @author li88qq
 * @version 1.0 2024/1/2 21:35
 */
public class GetRolePageForm extends PageForm {

    private String name;
    private Integer isAllMenu;

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
