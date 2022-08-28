package com.li88qq.admin.module.main.dto.role;

/**
 * 保存角色
 * @author li88qq
 * @version 1.0 2022/8/28 23:40
 */
public class SaveRoleForm {

    private String name;
    private String remark;

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
