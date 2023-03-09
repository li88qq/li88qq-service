package com.li88qq.db.interceptor.condition;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.dto.sql.NodeDto;
import com.li88qq.db.enums.Format;
import org.springframework.stereotype.Component;

/**
 * 字符串
 *
 * @author li88qq
 * @version 1.0 2023/3/5 20:22
 */
@Component
public class StringChain implements NodeChain {

    @Override
    public boolean check(Class<?> aClass) {
        return aClass == String.class;
    }

    @Override
    public void handle(ConditionChainManager manager, Condition condition, NodeDto nodeDto) {
        format(manager, condition, nodeDto);
        manager.buildTextSqlNode(condition, nodeDto);
    }

    @Override
    public void format(ConditionChainManager manager, Condition condition, NodeDto nodeDto) {
        Object value = nodeDto.getValue();
        Format f = condition.f();
        String formatValue = null;
        if (f == Format.LIKE) {
            formatValue = String.format("%s%s%s", "%", value, "%");
        }

        nodeDto.setFormatValue(formatValue);
        manager.checkParamToMap(nodeDto);
    }
}
