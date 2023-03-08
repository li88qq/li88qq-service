package com.li88qq.db.interceptor.condition;

import com.li88qq.db.annotion.Condition;
import org.apache.ibatis.executor.statement.StatementHandler;

import java.util.LinkedList;

/**
 * 条件职责链控制器
 *
 * @author li88qq
 * @version 1.0 2023/3/8 23:39
 */
public class ConditionChainManager {

    private final StatementHandler handler;
    private final Condition[] conditions;
    private final LinkedList<NodeChain> chains = new LinkedList<>();

    public ConditionChainManager(StatementHandler handler, Condition[] conditions) {
        this.handler = handler;
        this.conditions = conditions;
    }

    /**
     * 添加拦截链
     *
     * @param chain 链
     */
    public void add(NodeChain chain) {
        chains.add(chain);
    }

    /**
     * 执行
     */
    public void execute() {

    }

}
