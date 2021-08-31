package com.li88qq.service.entity;

import com.li88qq.service.utils.DateUtil;
import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * todo标签
 *
 * @author li88qq
 * @version 1.0 2021/8/31 23:28
 */
@Table(value = "ToDoLabel")
public class ToDoLabel {

    @Id
    private Long id;
    private Long uid = 0L;//用户id
    private String name;//标签名称
    private Integer sort = 0;//排序
    private Integer deleteState = 0;//删除标志,0-正常,1-删除
    private Long createDate = DateUtil.getTimestamp();//创建时间
    private Long updateDate = 0L;//更新时间

    public ToDoLabel() {
    }

    public ToDoLabel(Long uid, String name) {
        this.uid = uid;
        this.name = name;
    }

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

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }
}
