package com.li88qq.db.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明分页
 *
 * @author li88qq
 * @version 1.0 2023/2/20 22:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PageId {

    //自定义分页统计语句
    String value() default "";

    //自定义统计字段,默认为@Id指定值
    String countField() default "";

    //是否groupBy分页查询
    boolean groupBy() default false;

}
