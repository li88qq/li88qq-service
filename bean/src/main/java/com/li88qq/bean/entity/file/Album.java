package com.li88qq.bean.entity.file;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;
import com.li88qq.utils.DateUtil;

/**
 * 相册
 *
 * @author li88qq
 * @version 1.0 2022/5/8 23:04
 */
@Table
public class Album {

    @Id
    private Long id;
    private Long uid = 0L;//用户id
    private String name;//名称
    private String remark;//备注
    private Long createDate = DateUtil.getTimestamp();//创建日期

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
}
