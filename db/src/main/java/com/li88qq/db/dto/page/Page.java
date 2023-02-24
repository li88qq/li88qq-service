package com.li88qq.db.dto.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页接口转换对象,用于dao响应类型
 *
 * @author li88qq
 * @version 1.0 2023/2/20 23:30
 */
public class Page<T> extends ArrayList<T> {

    private int page; //当前页
    private int pageSize;//每页显示数
    private long total;//总数

    /**
     * 转化分页对象
     */
    public TPage<T> build() {
        TPageImpl<T> tPage = new TPageImpl<>();
        tPage.setPage(page);
        tPage.setPageSize(pageSize);
        tPage.setTotal(total);
        tPage.setContent(this);
        return tPage;
    }

    /**
     * 列表转换分页
     *
     * @param list 列表
     * @return 分页对象
     */
    public static <T> Page<T> convert(List<T> list) {
        Page<T> page = new Page<>();
        if (list != null) {
            page.addAll(list);
        }
        return page;
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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
