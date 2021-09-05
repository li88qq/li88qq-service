package com.li88qq.service.entity;

import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 拼音库
 *
 * @author li88qq
 * @version 1.0 2021/9/4 23:29
 */
@Table("PinYinLib")
public class PinYinLib {

    @Id
    private Long id;
    private String pinyin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
