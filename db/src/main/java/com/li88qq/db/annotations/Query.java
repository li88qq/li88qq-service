package com.li88qq.db.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * sql注解
 *
 * @author li88qq
 * @version 1.0 2021/12/27 23:28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    /**
     * 查询sql
     */
    String value();

    /**
     * 自定义分页统计字段,默认id
     */
    String countField() default "id";

    /**
     * 自定义分页统计语句
     */
    String countSql() default "";
}
