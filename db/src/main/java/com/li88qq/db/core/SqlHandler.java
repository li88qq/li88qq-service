package com.li88qq.db.core;

import com.li88qq.db.annotations.Condition;
import com.li88qq.db.annotations.Conditions;
import com.li88qq.db.annotations.Modifying;
import com.li88qq.db.annotations.Query;
import com.li88qq.db.dto.SqlMeta;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * sql处理器
 *
 * @author li88qq
 * @version 1.0 2022/2/26 23:47
 */
public class SqlHandler {

    /**
     * 处理sql
     *
     * @param method 当前代理方法
     * @return sql对象
     */
    public static SqlMeta handle(Method method) {
        //流程
        //1.校验sql是否正确
        //2.参数处理
        //3.sql拼接

        StringBuilder sql = new StringBuilder();

        Query query = method.getAnnotation(Query.class);
        Modifying modifying = method.getAnnotation(Modifying.class);
        Conditions annotation = method.getAnnotation(Conditions.class);
        Condition[] value = annotation.value();
        Parameter[] parameters = method.getParameters();

        return null;
    }

    /**
     * 校验方法
     *
     * @param method query方法
     */
    public static void checkMethod(Method method) {
        //1.参数校验
        //2.select
        //3.Modifying注解校验
        //4.占位符校验
    }

}
