package com.lynn;

import com.lynn.web.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void getUser() {
        System.out.println(userMapper.findUserByUserName("zk"));
    }

}
