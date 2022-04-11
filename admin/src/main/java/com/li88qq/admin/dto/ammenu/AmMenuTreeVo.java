package com.li88qq.admin.dto.ammenu;

/**
 * 菜单树
 *
 * @author li88qq
 * @version 1.0 2022/4/10 22:57
 */
public class AmMenuTreeVo {

    private Integer id;
    private Integer parent;
    private String name;
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
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
