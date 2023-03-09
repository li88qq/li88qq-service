package com.li88qq.db.interceptor.condition;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.dto.sql.NodeDto;

/**
 * 动态条件职责链
 *
 * @author li88qq
 * @version 1.0 2023/3/8 23:41
 */
public interface NodeChain {

    /**
     * 是否处理
     *
     * @param aClass 值类型
     * @return 是否处理
     */
    boolean check(Class<?> aClass);

    /**
     * 处理
     *
     * @param manager   职责链管理
     * @param condition 条件
     * @param nodeDto   NodeDto
     */
    void handle(ConditionChainManager manager, Condition condition, NodeDto nodeDto);

    /**
     * 格式化
     *
     * @param manager   职责链管理
     * @param condition 条件
     * @param nodeDto   NodeDto
     */
    default void format(ConditionChainManager manager, Condition condition, NodeDto nodeDto) {
    }
}
