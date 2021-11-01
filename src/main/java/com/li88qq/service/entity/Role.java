package com.li88qq.service.entity;

import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 角色
 *
 * @author li88qq
 * @version 1.0 2021/10/17 22:31
 */
@Table("Role")
public class Role {

    @Id
    private Long id;
    private String name;//名称
    private Integer allFlag = 0;//是否全部权限,0-否,1-是

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAllFlag() {
        return allFlag;
    }

    public void setAllFlag(Integer allFlag) {
        this.allFlag = allFlag;
    }
}
