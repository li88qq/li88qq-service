package com.li88qq.bean.entity.article;

import com.li88qq.db.annotion.Table;

/**
 * 文章和标签关联表
 *
 * @author li88qq
 * @version 1.0 2022/4/22 23:12
 */
@Table
public class Article_Label {

    private Long labelId;//标签id
    private Long articleId;//文章id

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
