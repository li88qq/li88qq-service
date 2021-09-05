package com.li88qq.service.entity;

import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 字-标签关联
 *
 * @author li88qq
 * @version 1.0 2021/9/5 22:46
 */
@Table(value = "Character_Label")
public class Character_Label {

    @Id
    private Long charId;
    private Long labelId;

    public Long getCharId() {
        return charId;
    }

    public void setCharId(Long charId) {
        this.charId = charId;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}
