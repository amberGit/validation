package com.example.validation.mapper;

import com.example.validation.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-09 10:58
 */
@Mapper
public interface UserMapper {

    void add(@Param("user") User user);

    int update(@Param("user") User user);
}
