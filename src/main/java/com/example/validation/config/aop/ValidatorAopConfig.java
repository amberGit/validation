package com.example.validation.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-12 19:21
 */
@Configuration
@Aspect
public class ValidatorAopConfig {

    @Around("execution(* com.example.validation.service.*Service.add*(..))")
    public Object validatorAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        return joinPoint.proceed(args);
    }
}
