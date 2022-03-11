package com.li88qq.db2.dto;

import java.util.List;

/**
 * 分页对象实现类,用于返回页面
 *
 * @author li88qq
 * @version 1.0 2022/3/11 19:31
 */
public class TPageImpl<T> implements TPage<T> {

    private int page;
    private int pageSize;
    private long total;
    private List<T> content;

    public TPageImpl() {
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public List<T> getContent() {
        return content;
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
