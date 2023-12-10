package com.li88qq.bean.entity.main.api;

import com.li88qq.db.annotion.Id;

/**
 * api文档内容
 *
 * @author li88qq
 * @version 1.0 2023/12/10 22:47
 */
public class ApiDoc {

    @Id
    private Long id;//主键,关联Api
    private String markdown;//md内容
    private String html;//html内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
