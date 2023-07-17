package com.li88qq.db.dto.page;

import java.util.Map;

/**
 * pageId
 *
 * @author li88qq
 * @version 1.0 2023/3/3 23:21
 */
public class PageIdDto {

    private String sql;//查询统计数量sql
    private Pageable pageable;//分页对象
    private Map<String, Object> paramMap;//参数列表
    private boolean groupBy;//是否groupBy

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public boolean isGroupBy() {
        return groupBy;
    }

    public void setGroupBy(boolean groupBy) {
        this.groupBy = groupBy;
    }
}
