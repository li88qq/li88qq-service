package com.li88qq.bean.entity.file;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;
import com.li88qq.utils.DateUtil;

/**
 * 图片
 *
 * @author li88qq
 * @version 1.0 2022/5/8 22:58
 */
@Table
public class Image {

    @Id
    private Long id;
    private Long uid = 0L;//用户id
    private String path;//路径
    private Integer imageType = 0;//类型
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
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
