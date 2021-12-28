package com.li88qq.db.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 条件
 *
 * @author li88qq
 * @version 1.0 2021/12/28 22:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {

    /**
     * 条件sql
     */
    String value();
}
