package com.li88qq.service.constant.annitions.validator;

import com.li88qq.service.constant.annitions.Password;
import com.li88qq.service.utils.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private String message;

    @Override
    public void initialize(Password password) {
        this.message = password.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.equals("")) {
            return ValidatorUtil.error(context, "请输入密码");
        }
        int min = 6;
        int max = 12;
        int length = value.length();
        if (length < min || length > max) {
            return ValidatorUtil.error(context, "密码格式错误");
        }
        String regex = "[a-zA-Z0-9_!@#$&]{6,12}";
        if (!value.matches(regex)) {
            return ValidatorUtil.error(context, "密码格式错误");
        }
        return true;
    }
}
