package com.li88qq.service.entity;

import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 字库
 *
 * @author li88qq
 * @version 1.0 2021/9/4 23:23
 */
@Table(value = "CharacterLib")
public class CharacterLib {

    @Id
    private Long id;
    private String hex;//16进制编码
    private String character;//字

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
