package com.example.validation.service;

import com.example.validation.exception.NoSuchAnnotationTypeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-12 17:58
 */
@Service
public class ValidatorService {

    private ClassPathScanningCandidateComponentProvider scanner;
    private Set<BeanDefinition> annotatedBeans;

    private Validator validator;

    @Value("${validator.base-packages}")
    private String basePackages;
    @Value("${validator.annotation-class-path}")
    private String annotationClassPath;



    public <T> Set<ConstraintViolation<T>> validate(T obj) {

        if (
                annotatedBeans.stream().anyMatch(
                        annotateBean -> annotateBean.getBeanClassName()
                                .equals(obj.getClass().getName())
                )
        ) {
            return validator.validate(obj);
        }
        return Collections.emptySet();
    }

    @PostConstruct
    private void init() throws ClassNotFoundException, NoSuchAnnotationTypeException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        scanner = new ClassPathScanningCandidateComponentProvider(false);

        Class annotationClass = Class.forName(annotationClassPath);
        if (annotationClass.isAnnotation()) {
            scanner.addIncludeFilter(new AnnotationTypeFilter(annotationClass));

            annotatedBeans = Arrays.stream(basePackages.split(","))
                    .flatMap(basePackage -> scanner.findCandidateComponents(basePackage).stream())
                    .collect(Collectors.toSet());
        }
        else {
            throw new NoSuchAnnotationTypeException();
        }

    }
}
