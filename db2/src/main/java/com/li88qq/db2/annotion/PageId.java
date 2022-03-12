package com.li88qq.db2.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分页注解,声明分页
 *
 * @author li88qq
 * @version 1.0 2022/3/11 22:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PageId {

    //自定义分页语句
    String value() default "";

    //count对象,默认id
    String countField() default "";

    // 是否统计总数
    boolean count() default true;

}
