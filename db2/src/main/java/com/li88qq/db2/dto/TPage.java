package com.li88qq.db2.dto;

import java.util.List;

/**
 * 分页对象,用于返回页面
 *
 * @author li88qq
 * @version 1.0 2022/3/11 19:22
 */
public interface TPage<T> {

    int getPage();

    int getPageSize();

    long getTotal();

    List<T> getContent();

}
