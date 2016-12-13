package com.example.validation;

import com.example.validation.annotation.Validation;
import com.example.validation.entity.User;
import com.example.validation.mapper.UserMapper;
import com.example.validation.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    @Qualifier("hibernateValidator")
    private Validator validator;

    @Autowired
    private UserService userService;

    private User user;

    @Test
    public void contextLoads() {
    }

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setName("amberD");
        user.setNumber(123L);
        user.setRemark("adhnfjpy23848ygh qaweh;odg-[1q234y 1cv");

    }

    @Test
    public void mybatisInterceptors() throws Exception {
        userMapper.add(user);

    }

    @Test
    public void scanAnnotations() throws Exception {

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(Validation.class));

        scanner.findCandidateComponents("com.example.validation")
                .forEach(item -> {
                    if (user.getClass().getName().equals(item.getBeanClassName())) {
                        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
                        violationSet.forEach(v -> System.out.println("invalid value: " + v.getInvalidValue()));

                    }
                });
    }


    @Test
    public void aopTest() throws Exception {
        userService.add(user);

    }
}
