package com.li88qq.bean.beans.system;

import com.li88qq.bean.utils.CreateDate;
import com.li88qq.db.annotion.Id;

/**
 * 菜单
 *
 * @author li88qq
 * @version 1.0 2023/12/16 11:32
 */
public class Menu {

    @Id
    private Integer id;//自增主键
    private Integer parentId = 0;//上级id
    private String parentIds;//上级节点id列表,逗号隔开
    private String name;//菜单名称
    private String href;//路径
    private String icon;//图标
    private Integer openType = 0;//打开方式,0-直接打开,1-新窗口打开
    private Integer keepalive = 0;//是否保持页面,0-否,1-是
    private Integer isDefault = 0;//是否默认菜单
    private Integer sort = 0;//排序,升序
    private Long createDate = CreateDate.now();//创建时间
    private Long updateDate = 0L;//更新时间

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

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
