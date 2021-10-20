package com.li88qq.service.response;

/**
 * 菜单
 *
 * @author li88qq
 * @version 1.0 2021/10/20 23:28
 */
public class MenuVo {

    private Long id;//主键id
    private String name;//名称
    private Long parentId;//上级id
    private String href;//路径
    private String icon;//图标
    private Integer sort;//排序,升序

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
