package com.li88qq.service.utils;

import javax.validation.ConstraintValidatorContext;

/**
 * 参数校验
 */
public class ValidatorUtil {

    public static boolean error(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }
}
