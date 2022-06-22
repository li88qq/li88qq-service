package com.li88qq.db.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 动态切换数据源
 *
 * @author li88qq
 * @version 1.0 2022/6/21 23:04
 */
public class DynamicDataSourceContextHolder {

    private static final Logger LOG = LogManager.getLogger();

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    /**
     * 切换数据源
     *
     * @param dataSource 数据源名称
     */
    public static void set(String dataSource) {
        LOG.info("切换数据源:{}", dataSource);
        holder.set(dataSource);
    }

    /**
     * 获取数据源
     *
     * @return 数据源名称
     */
    public static String get() {
        String dataSource = holder.get();
        LOG.info("获取数据源:{}", dataSource);
        return dataSource;
    }

    /**
     * 移除数据源
     */
    public static void remove() {
        LOG.info("移除数据源");
        holder.remove();
    }
}
