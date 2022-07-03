package com.li88qq.db.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注自定义查询
 *
 * @author li88qq
 * @version 1.0 2022/7/2 21:09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface NativeQuery {
}
