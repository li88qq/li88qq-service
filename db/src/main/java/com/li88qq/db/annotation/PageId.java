package com.li88qq.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分页注解
 *
 * @author li88qq
 * @version 1.0 2021/12/22 21:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PageId {

    //count字段,select count(id) from tb;
    String countField() default "id";

    //自定义countSql
    String countSql() default "";

}
