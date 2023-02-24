package com.li88qq.db.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 动态条件数组
 *
 * @author li88qq
 * @version 1.0 2023/2/20 22:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Conditions {

    //动态条件数组
    Condition[] value();
}
