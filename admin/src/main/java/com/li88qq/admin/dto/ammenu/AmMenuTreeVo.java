package com.li88qq.admin.dto.ammenu;

/**
 * 菜单树
 *
 * @author li88qq
 * @version 1.0 2022/4/10 22:57
 */
public class AmMenuTreeVo {

    private Integer id;
    private Integer parentId;
    private String name;
    private Integer sort;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
