package com.li88qq.service.request.article;

/**
 * @author li88qq
 * @version 1.0 2021/8/14 22:05
 */
public class UpdateContentBo {

    private Long id;
    private String content;

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
