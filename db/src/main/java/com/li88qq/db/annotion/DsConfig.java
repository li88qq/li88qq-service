package com.li88qq.db.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源配置
 *
 * @author li88qq
 * @version 1.0 2023/1/10 22:33
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DsConfig {

    //是否开启
    boolean enabled() default true;
    //是否动态多数据源
    boolean dynamic() default false;

    //多数据源前缀
    String prefix();
    //多数据源主数据源
    String primary();

}
