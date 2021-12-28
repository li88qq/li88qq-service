package com.li88qq.db.dto;

/**
 * 分页参数
 *
 * @author li88qq
 * @version 1.0 2021/12/28 23:14
 */
public interface Pageable {

    /**
     * 获取当前页
     */
    int getPageIndex();

    /**
     * 获取每页显示数量
     */
    int getPageSize();

    /**
     * 分页开始序号
     */
    int getOffset();
}
