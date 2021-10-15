package com.li88qq.service.constant.validator.constraint;

import com.li88qq.service.constant.validator.annitions.Ids;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * ids注解校验
 *
 * @author li88qq
 * @version 1.0 2021/10/15 22:42
 */
public class IdsValidator implements ConstraintValidator<Ids, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //是否需要校验
        //数据格式
        return true;
    }
}
