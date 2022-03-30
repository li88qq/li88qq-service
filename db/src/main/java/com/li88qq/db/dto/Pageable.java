package com.li88qq.db.dto;

/**
 * 分页对象,用于分页查询接口
 *
 * @author li88qq
 * @version 1.0 2022/3/11 19:22
 */
public class Pageable {

    private int page = 1;//当前页
    private int pageSize = 15;//每页显示数
    private final int pageNo;//开始记录

    public Pageable(Integer page, Integer pageSize) {
        if (page != null && page > 0) {
            this.page = page;
        }
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
        this.pageNo = (this.page - 1) * this.pageSize;
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

    public int getPageNo() {
        return pageNo;
    }
}
