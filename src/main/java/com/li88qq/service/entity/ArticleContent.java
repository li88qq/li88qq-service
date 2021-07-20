package com.li88qq.service.entity;

import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 文章内容
 */
@Table("ArticleContent")
public class ArticleContent {

    @Id
    private Long id;//文章id,和Article对应
    private String content;//内容

    public ArticleContent() {
    }

    public ArticleContent(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
