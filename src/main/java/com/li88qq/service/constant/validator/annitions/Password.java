package com.li88qq.service.constant.validator.annitions;

import com.li88qq.service.constant.validator.constraint.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message() default "密码格式错误";

    boolean require() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
