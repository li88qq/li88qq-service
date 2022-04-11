package com.li88qq.admin.dto.ammenu;

/**
 * 查询菜单
 *
 * @author li88qq
 * @version 1.0 2022/4/10 22:52
 */
public class AmMenuVo {

    private Integer parentId;
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
