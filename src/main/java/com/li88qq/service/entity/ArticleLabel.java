package com.li88qq.service.entity;

import com.li88qq.service.utils.DateUtil;
import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 文章标签
 */
@Table("ArticleLabel")
public class ArticleLabel {

    @Id
    private Long id;
    private String name;//标签名称
    private Long createUid = 0L;//创建人
    private Long createDate = DateUtil.getTimestamp();//创建时间

    public ArticleLabel() {
    }

    public ArticleLabel(String name, Long createUid) {
        this.name = name;
        this.createUid = createUid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
}
