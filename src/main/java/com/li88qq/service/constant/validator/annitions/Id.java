package com.li88qq.service.constant.validator.annitions;

import com.li88qq.service.constant.validator.constraint.IdValidator;
import com.li88qq.service.constant.validator.constraint.MobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * id注解
 *
 * @author li88qq
 * @version 1.0 2021/10/15 22:32
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdValidator.class)
public @interface Id {

    String message() default "";

    boolean require() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
