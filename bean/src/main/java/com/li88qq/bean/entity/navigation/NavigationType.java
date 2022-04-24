package com.li88qq.bean.entity.navigation;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;
import com.li88qq.utils.DateUtil;

/**
 * 导航类型
 *
 * @author li88qq
 * @version 1.0 2022/4/24 22:35
 */
@Table
public class NavigationType {

    @Id
    private Long id;
    private Long uid = 0L;//用户id
    private String name;//类型名称
    private String icon;//图标
    private String remark;//备注
    private Integer sort = 0;//排序
    private Long createDate = DateUtil.getTimestamp();//创建时间
    private Long updateDate = 0L;//更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
