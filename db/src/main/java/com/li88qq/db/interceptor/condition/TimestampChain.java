package com.li88qq.db.interceptor.condition;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.dto.sql.NodeDto;
import com.li88qq.db.enums.Format;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * 时间戳
 *
 * @author li88qq
 * @version 1.0 2023/3/5 20:23
 */
@Component
public class TimestampChain implements NodeChain {

    @Override
    public boolean check(Class<?> aClass) {
        return aClass == LocalDate.class || aClass == LocalDateTime.class;
    }

    @Override
    public void handle(ConditionChainManager manager, Condition condition, NodeDto nodeDto) {
        format(manager, condition, nodeDto);
        manager.buildTextSqlNode(condition, nodeDto);
    }

    @Override
    public void format(ConditionChainManager manager, Condition condition, NodeDto nodeDto) {
        Object value = nodeDto.getValue();
        Object formatValue = null;
        if (value instanceof LocalDate) {
            LocalDate localDate = (LocalDate) value;
            LocalTime localTime = LocalTime.MIN;
            Format f = condition.f();
            if (f == Format.TS_MAX) {
                localTime = LocalTime.MAX;
            }
            formatValue = localDate.atTime(localTime).toEpochSecond(ZoneOffset.of("+8"));
        }

        nodeDto.setFormatValue(formatValue);
        manager.checkParamToMap(nodeDto);
    }
}
