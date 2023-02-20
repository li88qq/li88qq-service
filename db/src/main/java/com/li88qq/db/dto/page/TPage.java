package com.li88qq.db.dto.page;

import java.util.List;

/**
 * 分页返回接口,用于返回页面
 *
 * @author li88qq
 * @version 1.0 2023/2/20 23:38
 */
public interface TPage<T> {

    /**
     * 获取当前页
     *
     * @return 当前页
     */
    int getPage();

    /**
     * 获取每页数量
     *
     * @return 每页数量
     */
    int getPageSize();

    /**
     * 获取总记录数
     *
     * @return 总记录数
     */
    long getTotal();

    /**
     * 获取分页内容
     *
     * @return 内容
     */
    List<T> getContent();
}
