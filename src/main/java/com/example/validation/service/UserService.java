package com.example.validation.service;

import com.example.validation.entity.User;
import com.example.validation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-13 10:19
 */
@Service
public class UserService {

    private final UserMapper mapper;

    @Autowired
    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }


    public void add(User user) {
        mapper.add(user);
    }

    public int update(User user) {
       return mapper.update(user);
    }
}
