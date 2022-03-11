package com.li88qq.db2.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 条件注解
 *
 * @author li88qq
 * @version 1.0 2022/3/11 20:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Conditions {

    Condition[] value();
}
