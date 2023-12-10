package com.li88qq.bean.entity.main.api;

import com.li88qq.bean.entity.utils.CreateDate;
import com.li88qq.db.annotion.Id;

/**
 * api文档
 *
 * @author li88qq
 * @version 1.0 2023/12/10 22:42
 */
public class Api {

    @Id
    private Long id;//自增主键
    private Integer uid = 0;//用户
    private Integer model = 0;//模板
    private String title;//标题
    private String rno;//随机码
    private String keyText;//搜索内容

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

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getKeyText() {
        return keyText;
    }

    public void setKeyText(String keyText) {
        this.keyText = keyText;
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
}
