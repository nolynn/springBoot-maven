package com.lynn.web.controller;

import com.lynn.web.entities.User;
import com.lynn.web.service.UserService;
import com.lynn.web.utils.BaseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Date: 2019/4/10 14:42
 * @Auther: lynn
 */
@RestController
@RequestMapping("/user")
@Api("用户模块")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/getUser")
    public BaseResult<User> getUser() {
        BaseResult<User> result = new BaseResult<>();
        String username = "zk";
        User user = userService.findUserByUserName(username);
        result.setData(user).setCode(200).setTotalCount(1);
        return result;
    }

}
