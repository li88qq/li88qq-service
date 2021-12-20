package com.li88qq.db.dto;

import java.util.List;

/**
 * 分页内容
 *
 * @author li88qq
 * @version 1.0 2021/12/15 23:37
 */
public class Page<T> {

    private int page;//当前页
    private int pageSize;//每页显示数
    private int total;//总记录数
    private List<T> content;//分页内容

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
