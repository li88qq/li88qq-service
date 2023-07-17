package com.li88qq.db.interceptor.condition;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.dto.sql.NodeDto;
import org.springframework.stereotype.Component;

/**
 * 数字
 *
 * @author li88qq
 * @version 1.0 2023/3/5 22:33
 */
@Component
public class NumberChain implements NodeChain {

    @Override
    public boolean check(Class<?> aClass) {
        return Number.class.isAssignableFrom(aClass);
    }

    @Override
    public void handle(ConditionChainManager manager, Condition condition, NodeDto nodeDto) {
        manager.buildTextSqlNode(condition, nodeDto);
    }
}
