package com.li88qq.bean.annotion;

import com.li88qq.bean.enums.ActionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作记录(后台)
 *
 * @author li88qq
 * @version 1.0 2022/5/23 23:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AmAcLog {

    ActionType actionType();//操作类型

    String prefix();//模块前缀

    String title();//标题

    String data() default "";//数据
}
