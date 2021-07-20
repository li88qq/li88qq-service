package com.li88qq.service.entity;

import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 文章和标签关联表
 */
@Table("Article_Label")
public class Article_Label {

    @Id
    private Long articleId;//文章id
    private Long labelId;//标签id

    public Article_Label() {
    }

    public Article_Label(Long articleId, Long labelId) {
        this.articleId = articleId;
        this.labelId = labelId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}
