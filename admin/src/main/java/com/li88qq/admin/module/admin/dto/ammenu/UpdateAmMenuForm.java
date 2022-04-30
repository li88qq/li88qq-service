package com.li88qq.admin.module.admin.dto.ammenu;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 修改菜单(后台)
 *
 * @author li88qq
 * @version 1.0 2022/4/10 22:48
 */
public class UpdateAmMenuForm {

    @NotNull
    @Min(value = 1, message = "参数错误")
    private Integer id;
    private Integer parentId;
    @NotNull(message = "请输入名称")
    @Size(min = 2, max = 20, message = "请输入名称")
    private String name;
    private String icon;
    private String url;
    private String router;
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

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
