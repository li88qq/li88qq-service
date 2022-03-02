package com.li88qq.db.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据
 *
 * @author li88qq
 * @version 1.0 2021/12/28 23:04
 */
public class PageImpl<T> implements Page<T> {

    private int page = 1;//当前页
    private int pageSize = 10;//每页数量
    private long totalNum;//总数量
    private List<T> content;//内容

    public PageImpl(Pageable pageable, long total, List<T> content) {
        if (total > 0) {
            this.totalNum = total;
        }
        if (content == null) {
            this.content = new ArrayList<>();
        }

        int pageIndex = pageable.getPageIndex();
        int pageSize = pageable.getPageSize();
        if (pageIndex > 0) {
            this.page = pageIndex;
        }
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int page() {
        return this.page;
    }

    public int pageSize() {
        return this.page;
    }

    public long total() {
        return this.totalNum;
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
