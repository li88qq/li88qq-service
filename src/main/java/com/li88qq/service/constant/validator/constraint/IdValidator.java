package com.li88qq.service.constant.validator.constraint;

import com.li88qq.service.constant.validator.annitions.Id;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * id注解校验
 *
 * @author li88qq
 * @version 1.0 2021/10/15 22:34
 */
public class IdValidator implements ConstraintValidator<Id, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        //是否必填
        return true;
    }
}
