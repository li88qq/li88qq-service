package com.li88qq.db.dto;

/**
 * 分页请求参数
 *
 * @author li88qq
 * @version 1.0 2021/12/20 23:34
 */
public class PageBo {

    private Integer page;//当前页
    private Integer pageSize;//每页显示数

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
