package com.li88qq.db2.annotion;

import java.lang.annotation.*;

/**
 * 条件语句
 *
 * @author li88qq
 * @version 1.0 2022/3/11 20:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Conditions.class)
public @interface Condition {

    String value();

}
