package com.li88qq.service.constant.validator.constraint;

import com.li88qq.service.constant.validator.annitions.Email;
import com.li88qq.service.utils.RegexUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.equals("")) {
            return true;
        }
        return RegexUtil.isEmail(value);
    }
}
