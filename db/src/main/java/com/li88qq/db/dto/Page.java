package com.li88qq.db.dto;

import java.util.List;

/**
 * 分页
 *
 * @author li88qq
 * @version 1.0 2021/12/28 23:00
 */
public interface Page<T> {

    int page();

    int pageSize();

    long total();

    List<T> content();
}
