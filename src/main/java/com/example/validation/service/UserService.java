package com.example.validation.service;

import com.example.validation.entity.User;
import com.example.validation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-13 10:19
 */
@Repository
public class UserService {

    @Autowired
    private UserMapper mapper;


    public void add(User user) {
        mapper.add(user);
    }
}
