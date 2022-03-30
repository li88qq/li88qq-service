package com.li88qq.db.dto;

/**
 * 分页查询form
 *
 * @author li88qq
 * @version 1.0 2022/3/30 21:53
 */
public class PageForm {

    private Integer page;
    private Integer pageSize;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
