package com.li88qq.service.module.system.dto.menu;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author li88qq
 * @version 1.0 2024/1/2 22:30
 */
public class UpdateMenuForm {

    @NotNull
    @Min(value = 1)
    private Integer id;

    @NotBlank(message = "名称不能为空")
    private String name;//菜单名称

    private String href;//路径
    private String icon;//图标
    private Integer openType;//打开方式,0-直接打开,1-新窗口打开
    private Integer keepalive;//保持状态
    private Integer sort;//排序,升序

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

    public Integer getKeepalive() {
        return keepalive;
    }

    public void setKeepalive(Integer keepalive) {
        this.keepalive = keepalive;
    }
}
