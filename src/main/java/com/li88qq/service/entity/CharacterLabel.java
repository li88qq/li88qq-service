package com.li88qq.service.entity;

import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 字标签
 *
 * @author li88qq
 * @version 1.0 2021/9/5 22:45
 */
@Table(value = "CharacterLabel")
public class CharacterLabel {

    @Id
    private Long id;
    private String name;

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
}
