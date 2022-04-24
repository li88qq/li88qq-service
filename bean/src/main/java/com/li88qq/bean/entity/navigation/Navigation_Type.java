package com.li88qq.bean.entity.navigation;

import com.li88qq.db.annotion.Table;

/**
 * 导航分类关系
 *
 * @author li88qq
 * @version 1.0 2022/4/24 22:39
 */
@Table
public class Navigation_Type {

    private Long typeId;//分类id
    private Long navId;//导航id

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getNavId() {
        return navId;
    }

    public void setNavId(Long navId) {
        this.navId = navId;
    }
}
