package com.li88qq.db.dto;

import java.util.List;

/**
 * 分页数据
 *
 * @author li88qq
 * @version 1.0 2021/12/28 23:04
 */
public class PageImpl<T> implements Page<T> {

    private int page;//当前页
    private int pageSize;//每页数量
    private List<T> content;//内容

    public PageImpl(int page, int pageSize, List<T> content) {
        this.page = page;
        this.pageSize = pageSize;
        this.content = content;
    }

    public PageImpl(Pageable pageable, List<T> content) {
        this.page = pageable.getPageIndex();
        this.pageSize = pageable.getPageSize();
        this.content = content;
    }

    public int page() {
        return this.page;
    }

    public int pageSize() {
        return this.page;
    }

    public long total() {
        return this.content.size();
    }

    public List<T> content() {
        return this.content;
    }

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

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
