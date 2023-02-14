package com.li88qq.db.annotion;

import com.li88qq.db.enums.Format;
import com.li88qq.db.enums.If;
import com.li88qq.db.enums.Join;

import java.lang.annotation.*;

/**
 * 动态条件语句
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

    //条件成立判断
    If i() default If.NOT_NULL;

    //数据格式化处理
    Format f() default Format.NONE;

    //连接符,见Join,默认and
    Join j() default Join.AND;

}
