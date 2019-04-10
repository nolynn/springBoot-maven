package com.lynn.web.mapper;

import com.lynn.web.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User findUserByUserName(@Param("username") String username);
}
