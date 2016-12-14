package com.example.validation.annotation;

import com.example.validation.config.validator.ChineseNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-14 18:42
 */
@Constraint(validatedBy = ChineseNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChineseName {
    String message() default "必须符合中文名规范";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
