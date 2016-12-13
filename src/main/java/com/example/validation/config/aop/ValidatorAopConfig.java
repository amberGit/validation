package com.example.validation.config.aop;

import com.example.validation.service.ValidatorService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-12 19:21
 */
@Configuration
@Aspect
public class ValidatorAopConfig {

    @Autowired
    private ValidatorService validatorService;

//    @Around("execution(* com.example.validation.service.*Service.add*(..))")
    public Object validatorAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        Stream.of(args).flatMap(arg -> validatorService.validate(arg).stream())
                .forEach(item -> System.out.println("error message: " + item.getMessage()));

        return joinPoint.proceed(args);
    }
}
