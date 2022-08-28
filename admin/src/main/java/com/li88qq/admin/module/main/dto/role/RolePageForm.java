package com.li88qq.admin.module.main.dto.role;

import com.li88qq.db.dto.PageForm;

/**
 * 分页
 *
 * @author li88qq
 * @version 1.0 2022/8/28 23:43
 */
public class RolePageForm extends PageForm {

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
