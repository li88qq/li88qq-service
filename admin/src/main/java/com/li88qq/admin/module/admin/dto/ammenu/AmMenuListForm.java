package com.li88qq.admin.module.admin.dto.ammenu;

/**
 * 查询菜单列表
 *
 * @author li88qq
 * @version 1.0 2022/4/11 23:29
 */
public class AmMenuListForm {

    private String name;
    private Integer state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
