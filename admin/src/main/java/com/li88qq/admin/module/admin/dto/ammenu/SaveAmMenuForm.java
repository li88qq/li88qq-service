package com.li88qq.admin.module.admin.dto.ammenu;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 保存菜单
 *
 * @author li88qq
 * @version 1.0 2022/4/10 22:40
 */
public class SaveAmMenuForm {

    private Integer parentId;
    @NotNull(message = "请输入名称")
    @Size(min = 2, max = 20, message = "请输入名称")
    private String name;
    private String icon;
    private String url;
    private String router;
    private Integer sort;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }
}
