package com.li88qq.admin.module.main.dto.menu;

import javax.validation.constraints.NotNull;

/**
 * 保存菜单
 *
 * @author li88qq
 * @version 1.0 2022/8/11 23:21
 */
public class SaveMenuForm {

    @NotNull(message = "请输入名称")
    private String name;
    private Integer parentId;
    private String icon;
    private String url;
    private String router;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
}
