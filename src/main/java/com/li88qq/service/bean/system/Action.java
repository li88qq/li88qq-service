package com.li88qq.service.bean.system;

import com.li88qq.db.annotion.Id;

/**
 * 动作
 *
 * @author li88qq
 * @version 1.0 2023/10/11 22:43
 */
public class Action {

    @Id
    private Integer id;
    private String name;//名称
    private String code;//编码,格式:类前缀:动作,如:User:Save
    private Integer type = 0;//类型,

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
