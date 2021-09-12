package com.li88qq.service.entity;

import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 题目答案
 *
 * @author li88qq
 * @version 1.0 2021/9/12 23:06
 */
@Table("CodeAnswer")
public class CodeAnswer {

    @Id
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
