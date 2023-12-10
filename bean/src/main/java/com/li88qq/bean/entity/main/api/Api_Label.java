package com.li88qq.bean.entity.main.api;

import com.li88qq.db.annotion.Id;


/**
 * 文档标签关系
 *
 * @author li88qq
 * @version 1.0 2023/12/10 22:55
 */
public class Api_Label {

    @Id
    private Long apiId;//文档id
    @Id
    private Integer labelId;//标签id

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }
}
