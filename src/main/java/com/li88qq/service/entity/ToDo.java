package com.li88qq.service.entity;

import com.li88qq.service.utils.DateUtil;
import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * todo实体
 *
 * @author li88qq
 * @version 1.0 2021/8/31 23:33
 */
@Table(value = "ToDo")
public class ToDo {

    @Id
    private Long id;
    private Long uid = 0L;
    private Long labelId = 0L;//标签id
    private String content;//内容
    private Integer state = 0;//状态,0-新建,1-已完成,
    private Long finishDate = 0L;//完成时间
    private Integer sort = 0;//排序,重要级别,倒序,
    private Long beginDate = 0L;//计划开始时间
    private Long endDate = 0L;//计划结束时间

    private Long createDate = DateUtil.getTimestamp();//创建时间
    private Long updateDate = 0L;//修改时间

    public ToDo() {
    }

    public ToDo(Long uid, Long labelId, String content) {
        this.uid = uid;
        this.labelId = labelId;
        this.content = content;
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

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Long finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Long beginDate) {
        this.beginDate = beginDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
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
