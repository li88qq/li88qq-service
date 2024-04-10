package com.li88qq.service.module.system.dto.menu;

/**
 * @author li88qq
 * @version 1.0 2024/1/2 22:24
 */
public class GetMenuListVo {

    private Integer id;//自增主键
    private Integer parentId;//上级id
    private String name;//菜单名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
