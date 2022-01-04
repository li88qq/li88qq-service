package com.li88qq.db.annotations;

import com.li88qq.db.config.MapperScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扫描注解
 *
 * @author li88qq
 * @version 1.0 2021/12/28 22:46
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({MapperScannerRegistrar.class})
public @interface MapperScan {

    /**
     * 扫描mapper位置
     */
    String[] basePackages() default {};
}
