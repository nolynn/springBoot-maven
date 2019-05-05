package com.lynn.web.service.impl;

import com.lynn.web.entities.User;
import com.lynn.web.mapper.UserMapper;
import com.lynn.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Date: 2019/4/4 16:31
 * @Auther: lynn
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }

    @Override
    public User register(String username, String password) {
        return userMapper.resister(username, password);
    }
}
