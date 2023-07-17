package com.li88qq.db.dto.threadlocal;

import com.li88qq.db.dto.page.PageIdDto;

/**
 * 存储PageDto
 *
 * @author li88qq
 * @version 1.0 2023/3/3 23:20
 */
public class PageIdThreadLocal {

    private static final ThreadLocal<PageIdDto> HOLDER = new ThreadLocal<>();

    /**
     * 取
     *
     * @return PageIdDto
     */
    public static PageIdDto get() {
        return HOLDER.get();
    }

    /**
     * 设置
     *
     * @param dto PageIdDto
     */
    public static void set(PageIdDto dto) {
        HOLDER.set(dto);
    }

    /**
     * 销毁
     */
    public static void remove() {
        HOLDER.remove();
    }

}
