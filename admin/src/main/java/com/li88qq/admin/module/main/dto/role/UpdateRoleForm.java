package com.li88qq.admin.module.main.dto.role;

/**
 * 修改
 * @author li88qq
 * @version 1.0 2022/8/28 23:41
 */
public class UpdateRoleForm {

    private Integer id;
    private String name;
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}