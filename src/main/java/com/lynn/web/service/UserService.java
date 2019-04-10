package com.lynn.web.service;

import com.lynn.web.entities.User;

/**
 * @Description:
 * @Date: 2019/4/4 16:31
 * @Auther: lynn
 */
public interface UserService {

    User findUserByUserName(String username);
}
