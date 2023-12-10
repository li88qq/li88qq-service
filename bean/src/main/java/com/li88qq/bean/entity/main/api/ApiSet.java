package com.li88qq.bean.entity.main.api;

import com.li88qq.bean.entity.utils.CreateDate;
import com.li88qq.db.annotion.Id;

/**
 * 文档集
 *
 * @author li88qq
 * @version 1.0 2023/12/10 22:48
 */
public class ApiSet {

    @Id
    private Long id;//自增主键
    private Integer uid = 0;//用户
    private String rid;//随机码
    private String title;//标题
    private String desc;//描述
    private Long createDate = CreateDate.now();//创建时间
    private Long updateDate = 0L;//最后编辑时间

    private Integer readCount = 0;//阅读次数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
