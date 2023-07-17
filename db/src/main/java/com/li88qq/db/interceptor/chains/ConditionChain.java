package com.li88qq.db.interceptor.chains;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.Conditions;
import com.li88qq.db.interceptor.condition.*;
import jakarta.annotation.Resource;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 动态条件
 *
 * @author li88qq
 * @version 1.0 2023/3/1 22:07
 */
@Component
public class ConditionChain implements InterceptorChain {

    @Resource
    private StringChain stringChain;
    @Resource
    private TimestampChain timestampChain;
    @Resource
    private ArrayChain arrayChain;
    @Resource
    private NumberChain numberChain;

    @Override
    public boolean execute(StatementHandler handler, Method method) {
        //是否有参数
        int parameterCount = method.getParameterCount();
        if (parameterCount == 0) {
            return true;
        }

        //判断注解
        Conditions conditions = method.getDeclaredAnnotation(Conditions.class);
        Condition condition = method.getDeclaredAnnotation(Condition.class);

        Condition[] conditionArray = null;
        if (condition != null) {
            conditionArray = new Condition[]{condition};
        }
        if (conditions != null) {
            conditionArray = conditions.value();
        }

        if (conditionArray == null || conditionArray.length == 0) {
            return true;
        }

        //判断where

        //职责链
        ConditionChainManager manager = new ConditionChainManager(handler, conditionArray);
        manager.add(numberChain);//数字
        manager.add(stringChain);//字符串
        manager.add(timestampChain);//时间戳
        manager.add(arrayChain);//列表和数组

        manager.execute();
        return true;
    }
}
