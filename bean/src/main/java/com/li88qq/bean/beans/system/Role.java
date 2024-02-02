package com.li88qq.bean.beans.system;

import com.li88qq.db.annotion.Id;

/**
 * 角色
 *
 * @author li88qq
 * @version 1.0 2023/12/16 11:32
 */
public class Role {

    @Id
    private Integer id;//自增主键
    private String name;//名称
    private Integer isAllMenu = 0;//是否授权全部菜单
    private Integer isRoot = 0;//是否root

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

    public Integer getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Integer isRoot) {
        this.isRoot = isRoot;
    }
}
