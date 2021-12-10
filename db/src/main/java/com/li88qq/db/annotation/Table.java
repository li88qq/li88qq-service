package com.li88qq.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表名注解
 *
 * @author li88qq
 * @version 1.0 2021/12/10 23:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    /**
     * 表名
     *
     * @return 如果有值, 则取该值, 否则取类名
     */
    String name() default "";
}
