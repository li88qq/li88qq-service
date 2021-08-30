package com.li88qq.service.request.nav;

import com.li88qq.service.dto.PageBo;

/**
 * @author li88qq
 * @version 1.0 2021/8/20 23:23
 */
public class GetNavPageBo extends PageBo {

    private Long typeId;
    private String name;
    private String url;
    private String remark;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}