package com.li88qq.bean.entity.article;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;

/**
 * 文章内容
 *
 * @author li88qq
 * @version 1.0 2022/4/22 23:14
 */
@Table
public class ArticleDoc {

    @Id
    private Long id;//文章id
    private String doc;//文章内容
    private String originalDoc;//原内容
    private Long updateDate = 0L;//更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getOriginalDoc() {
        return originalDoc;
    }

    public void setOriginalDoc(String originalDoc) {
        this.originalDoc = originalDoc;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
