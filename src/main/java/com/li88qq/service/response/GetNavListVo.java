package com.li88qq.service.response;

import java.util.List;

/**
 * @author li88qq
 * @version 1.0 2021/8/21 10:21
 */
public class GetNavListVo {

    private Long typeId;
    private String typeName;
    private List<NavVo> list;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<NavVo> getList() {
        return list;
    }

    public void setList(List<NavVo> list) {
        this.list = list;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
