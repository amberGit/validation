package com.example.validation.config.validator;

import com.example.validation.annotation.ChineseName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-14 18:51
 */
public class ChineseNameValidator implements ConstraintValidator<ChineseName, String> {
    private static final String PATTERN =  "[\\u4e00-\\u9fa5]+(?:·[\\u4e00-\\u9fa5]+)*";

    @Override
    public void initialize(ChineseName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = value.matches(PATTERN);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("非法的中文名字")
                .addConstraintViolation();
        }
        return isValid;
    }
}
