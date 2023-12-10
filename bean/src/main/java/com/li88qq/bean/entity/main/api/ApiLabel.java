package com.li88qq.bean.entity.main.api;

import com.li88qq.db.annotion.Id;

/**
 * 文档标签
 *
 * @author li88qq
 * @version 1.0 2023/12/10 22:54
 */
public class ApiLabel {

    @Id
    private Integer id;
    private String name;

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
