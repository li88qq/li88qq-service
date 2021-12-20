package com.li88qq.db.dto;

/**
 * 分页参数
 *
 * @author li88qq
 * @version 1.0 2021/12/11 22:53
 */
public class Pageable {

    private int page;//当前页
    private int pageSize;//每页显示数

    public Pageable() {
    }

    public Pageable(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
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
}
