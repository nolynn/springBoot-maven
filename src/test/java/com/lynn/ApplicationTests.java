package com.lynn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lynn.web.entities.User;
import com.lynn.web.mapper.UserMapper;
import com.lynn.web.utils.BaseResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    UserMapper userMapper;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getUser() {

        String jsonStr = restTemplate.getForObject("http://localhost:8080/user/getUser?username=zk&age=-1", String.class);
        TypeReference reference = new TypeReference<BaseResult<User>>() {
        };
        BaseResult<User> baseResult = (BaseResult<User>) JSON.parseObject(jsonStr, reference);
        System.out.println(baseResult);
    }

    @Test
    public void loginTest() {
    }

}
