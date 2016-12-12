package com.example.validation.config.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-12 16:26
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public Validator hibernateValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

}
