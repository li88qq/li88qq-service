package com.li88qq.db.interceptor.condition;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.dto.sql.NodeDto;
import com.li88qq.db.enums.Format;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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
        String value = nodeDto.getValue().toString().trim();
        // 如果是空字符串,不处理
        if(value.isEmpty()){
            return;
        }
        format(manager, condition, nodeDto);
        Object formatValue = nodeDto.getFormatValue();
        if (formatValue != null && formatValue.getClass().isArray()) {
            manager.buildForEachSqlNode(condition, nodeDto);
        } else {
            manager.buildTextSqlNode(condition, nodeDto);
        }
    }

    @Override
    public void format(ConditionChainManager manager, Condition condition, NodeDto nodeDto) {
        String value = nodeDto.getValue().toString().trim();
        Format f = condition.f();
        Object formatValue = null;

        //Like和转数组
        if (f == Format.LIKE) {
            formatValue = String.format("%s%s%s", "%", value, "%");
        } else if (f == Format.LIKE_L) {
            formatValue = String.format("%s%s", "%", value);
        } else if (f == Format.LIKE_R) {
            formatValue = String.format("%s%s", value, "%");
        } else if (f == Format.LIST_N) {
            String[] valueArray = value.split(",");
            BigDecimal[] formatList = new BigDecimal[valueArray.length];
            for (int i = 0; i < valueArray.length; i++) {
                formatList[i] = new BigDecimal(valueArray[i]);
            }
            formatValue = formatList;
        } else if (f == Format.LIST_S) {
            formatValue = value.split(",");
        }

        nodeDto.setFormatValue(formatValue);
        manager.checkParamToMap(nodeDto);
    }
}
