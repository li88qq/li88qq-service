package com.li88qq.service.entity;

import com.li88qq.service.utils.DateUtil;
import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 文章阅读记录
 */
@Table("ArticleReadLog")
public class ArticleReadLog {

    @Id
    private Long id;
    private Long articleId = 0L;//文章id
    private Long uid = 0L;//用户id
    private Long createDate = DateUtil.getTimestamp();
    private String ip;//ip

    public ArticleReadLog() {
    }

    public ArticleReadLog(Long articleId, Long uid, String ip) {
        this.articleId = articleId;
        this.uid = uid;
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
