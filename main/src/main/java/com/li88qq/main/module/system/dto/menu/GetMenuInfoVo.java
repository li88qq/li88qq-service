package com.li88qq.main.module.system.dto.menu;

/**
 * @author li88qq
 * @version 1.0 2023/12/16 18:06
 */
public class GetMenuInfoVo {

    private Integer parentId;//上级id
    private String parentIds;//上级节点id列表,逗号隔开
    private String name;//菜单名称
    private String href;//路径
    private String icon;//图标
    private Integer openType;//打开方式,0-直接打开,1-新窗口打开
    private Integer keepalive;//是否保持页面,0-否,1-是
    private Integer sort;//排序,升序

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
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

    public Integer getKeepalive() {
        return keepalive;
    }

    public void setKeepalive(Integer keepalive) {
        this.keepalive = keepalive;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
