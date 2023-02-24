package com.li88qq.db.dto.page;

import java.util.List;

/**
 * 分页响应内容
 *
 * @author li88qq
 * @version 1.0 2023/2/20 23:39
 */
public class TPageImpl<T> implements TPage<T> {

    private int page;//当前页
    private int pageSize;//每页显示数量
    private long total;//总页数
    private List<T> content;//内容

    public TPageImpl() {
    }

    @Override
    public int getPage() {
        return this.page;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public List<T> getContent() {
        return this.content;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
