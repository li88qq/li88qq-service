package com.li88qq.service.constant.validator.annitions;

import com.li88qq.service.constant.validator.constraint.IdsValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ids注解
 *
 * @author li88qq
 * @version 1.0 2021/10/15 22:32
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdsValidator.class)
public @interface Ids {
}
