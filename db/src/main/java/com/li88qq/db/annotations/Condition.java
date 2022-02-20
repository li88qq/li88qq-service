package com.li88qq.db.annotations;

import java.lang.annotation.*;

/**
 * 条件
 *
 * @author li88qq
 * @version 1.0 2021/12/28 22:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Conditions.class)
public @interface Condition {

    /**
     * 条件sql
     */
    String value();
}
