package com.li88qq.db.annotion;

import com.li88qq.db.enums.Format;

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

    //条件语句
    String value();

    //连接符,见JoinMark,默认and
    String joinMark() default "";

    //数据处理
    Format format() default Format.NONE;

}
