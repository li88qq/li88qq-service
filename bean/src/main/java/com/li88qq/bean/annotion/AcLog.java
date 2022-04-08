package com.li88qq.bean.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作记录
 *
 * @author li88qq
 * @version 1.0 2022/4/8 23:48
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AcLog {

    int actionType();//操作类型

    String prefix();//模块前缀

    String title();//标题

    String data();//数据

}
