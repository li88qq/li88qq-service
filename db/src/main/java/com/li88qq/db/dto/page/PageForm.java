package com.li88qq.db.dto.page;

/**
 * 分页请求参数,用于前台请求参数
 *
 * @author li88qq
 * @version 1.0 2023/2/20 23:45
 */
public class PageForm {

    private Integer page;//当前页,从1开始
    private Integer pageSize;//每页显示数量

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
