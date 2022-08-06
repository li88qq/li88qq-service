package com.li88qq.bean.entity.system;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;
import com.li88qq.utils.DateUtil;

/**
 * 角色
 *
 * @author li88qq
 * @version 1.0 2022/8/6 23:36
 */
@Table
public class Role {

    @Id
    private Integer id;//id
    private String name;//名称
    private String remark;//备注
    private Long createDate = DateUtil.getTimestamp();//创建时间

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

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
}
