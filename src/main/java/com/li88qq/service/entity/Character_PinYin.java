package com.li88qq.service.entity;

import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 字-拼音关联
 *
 * @author li88qq
 * @version 1.0 2021/9/5 22:48
 */
@Table(value = "Character_PinYin")
public class Character_PinYin {

    @Id
    private Long charId;
    private Long pinYinId;

    public Long getCharId() {
        return charId;
    }

    public void setCharId(Long charId) {
        this.charId = charId;
    }

    public Long getPinYinId() {
        return pinYinId;
    }

    public void setPinYinId(Long pinYinId) {
        this.pinYinId = pinYinId;
    }
}
