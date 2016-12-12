package com.example.validation.service;

import com.example.validation.exception.NoSuchAnnotationTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-12 17:58
 */
@Service
public class ValidatorService {

    private ClassPathScanningCandidateComponentProvider scanner;
    private Set<BeanDefinition> annotatedBeans;

    private final Validator validator;

    @Value("${validator.base-packages}")
    private String basePackages;
    @Value("${validator.annotation-class-path")
    private String annotationClassPath;

    @Autowired
    public ValidatorService(@Qualifier("hibernateValidator") Validator validator) {
        this.validator = validator;
    }


    public <T> Set<ConstraintViolation<T>> validate(T obj) {

        for (BeanDefinition annotatedBean: annotatedBeans) {
            if (obj.getClass().getName().equals(annotatedBean.getBeanClassName())) {
                return validator.validate(obj);
            }
        }
        return new HashSet<>();
    }

    @PostConstruct
    private void init() throws ClassNotFoundException, NoSuchAnnotationTypeException {
        scanner = new ClassPathScanningCandidateComponentProvider(false);
        Class annotationClass = Class.forName(annotationClassPath);
        if (annotationClass.isAnnotation()) {
            scanner.addIncludeFilter(new AnnotationTypeFilter(annotationClass));
            for (String basePackage: basePackages.split(",")) {
                annotatedBeans.addAll(scanner.findCandidateComponents(basePackage));
            }
        }
        else {
            throw new NoSuchAnnotationTypeException();
        }

    }
}
