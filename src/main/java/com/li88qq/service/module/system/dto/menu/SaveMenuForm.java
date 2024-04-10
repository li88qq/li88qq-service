package com.li88qq.service.module.system.dto.menu;

import jakarta.validation.constraints.NotBlank;

/**
 * @author li88qq
 * @version 1.0 2023/12/16 17:06
 */
public class SaveMenuForm {

    @NotBlank(message = "名称不能为空")
    private String name;//菜单名称
    private Integer parentId;//上级id
    private String href;//路径
    private String icon;//图标
    private Integer openType;//打开方式,0-直接打开,1-新窗口打开
    private Integer sort;//排序,升序

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

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
