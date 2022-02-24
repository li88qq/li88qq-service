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

    /**
     * 对应where位置索引,应用于可能存在的多个where条件位置
     */
    int index() default 0;

    /**
     * 条件保存策略
     */
    String[] retain() default {};
}
