package com.li88qq.db.annotion;

import com.li88qq.db.enums.Format;
import com.li88qq.db.enums.If;
import com.li88qq.db.enums.Join;

import java.lang.annotation.*;

/**
 * 动态条件
 *
 * @author li88qq
 * @version 1.0 2023/2/20 22:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(value = Conditions.class)
public @interface Condition {

    //条件语句
    String value();

    //字段格式化
    Format f() default Format.NONE;

    //动态条件语句成立条件
    If i() default If.NOT_NULL;

    //条件连接符
    Join j() default Join.AND;

    //where插入位置,用于存在多个where位置,默认为:where
    String where() default "";
}
