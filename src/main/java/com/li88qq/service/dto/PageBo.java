package com.li88qq.service.dto;

public class PageBo {

    private Integer page = 1;
    private Integer size = 15;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}