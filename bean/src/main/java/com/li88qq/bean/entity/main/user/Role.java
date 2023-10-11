package com.li88qq.bean.entity.main.user;

import com.li88qq.db.annotion.Id;

/**
 * 角色
 *
 * @author li88qq
 * @version 1.0 2023/10/11 22:41
 */
public class Role {

    @Id
    private Integer id;
    private String name;//名称

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
}
