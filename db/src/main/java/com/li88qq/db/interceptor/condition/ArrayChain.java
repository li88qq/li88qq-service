package com.li88qq.db.interceptor.condition;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.dto.sql.NodeDto;
import com.li88qq.db.enums.Format;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 列表
 *
 * @author li88qq
 * @version 1.0 2023/3/5 20:23
 */
@Component
public class ArrayChain implements NodeChain {

    @Override
    public boolean check(Class<?> aClass) {
        return Collection.class.isAssignableFrom(aClass) || aClass.isArray();
    }

    @Override
    public void handle(ConditionChainManager manager, Condition condition, NodeDto nodeDto) {
        //1.需要声明 Format.IN
        Format f = condition.f();
        if (f != Format.IN) {
            return;
        }
        //2.列表数量需大于0
        Object value = nodeDto.getValue();
        if (value instanceof Collection) {
            if (((Collection<?>) value).isEmpty()) {
                return;
            }
        } else if (value instanceof Object[]) {
            if (((Object[]) value).length == 0) {
                return;
            }
        }

        manager.buildForEachSqlNode(condition, nodeDto);
    }
}
