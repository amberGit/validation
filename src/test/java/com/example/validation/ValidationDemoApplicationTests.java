package com.example.validation;

import com.example.validation.entity.User;
import com.example.validation.mapper.UserMapper;
import com.example.validation.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

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
    public void aopTest() throws Exception {
        userService.add(user);

    }

    @Test
    public void updateTest() throws Exception {
        user.setId(1);
        int affectRows = userService.update(user);

    }
}
