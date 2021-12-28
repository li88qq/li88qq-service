package com.li88qq.db.dto;

/**
 * 分页参数
 *
 * @author li88qq
 * @version 1.0 2021/12/28 23:16
 */
public class PageableImpl implements Pageable {

    private int page = 1;//当前页
    private int size = 10;//每页显示数量

    public PageableImpl(int page, int size) {
        if (page > 1) {
            this.page = page;
        }
        if (size > 1) {
            this.size = size;
        }
    }

    public int getPageIndex() {
        return this.page;
    }

    public int getPageSize() {
        return this.size;
    }

    public int getOffset() {
        return (this.page - 1) * this.size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
