package com.li88qq.db.core;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态切换数据源
 *
 * @author li88qq
 * @version 1.0 2022/6/21 23:02
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 获取数据源
     *
     * @return 获取数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.get();
    }
}
