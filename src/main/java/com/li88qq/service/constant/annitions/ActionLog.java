package com.li88qq.service.constant.annitions;

import com.li88qq.service.constant.enumeration.ActionType;
import com.li88qq.service.constant.enumeration.SaveStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作记录
 *
 * @author li88qq
 * @version 1.0 2021/8/11 23:24
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionLog {

    SaveStrategy strategy() default SaveStrategy.SUCCESS;//保存策略

    ActionType acType();//操作类型

    String title();//标题

    String detail();//详情

}
