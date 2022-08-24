package com.li88qq.admin.module.main.dto.menu;

/**
 * 查询菜单
 *
 * @author li88qq
 * @version 1.0 2022/8/11 23:28
 */
public class MenuVo {

    private Integer id;
    private String name;//名称
    private Integer parentId;//父节点id
    private String icon;//图标
    private String url;//路径
    private String router;//路由
    private Integer sort;//排序
    private Integer state;//状态

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
