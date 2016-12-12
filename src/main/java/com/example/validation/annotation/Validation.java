package com.example.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-12 10:16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Validation {

}
