package com.li88qq.bean.entity.article;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;
import com.li88qq.utils.DateUtil;

/**
 * 文章标签
 *
 * @author li88qq
 * @version 1.0 2022/4/22 23:09
 */
@Table
public class ArticleLabel {

    @Id
    private Long id;
    private String name;//标签名称
    private Long createDate = DateUtil.getTimestamp();//创建时间
    private Long createUid = 0L;//创建人

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

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }
}
