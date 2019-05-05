package com.lynn.web.controller;

import com.lynn.web.entities.User;
import com.lynn.web.entities.params.UserSelectVo;
import com.lynn.web.service.UserService;
import com.lynn.web.utils.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description:
 * @Date: 2019/4/28 14:47
 * @Auther: lynn
 */
@RestController
@Api(tags = "事务控制模块测试")
public class TransactionalController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    UserService userService;

    @GetMapping("/transactional/test")
    @ApiModelProperty(value = "测试事务控制")
    public BaseResult<User> test(@Valid UserSelectVo userSelectVo) {
        BaseResult<User> result = new BaseResult<>();
        User user = userService.register("zhangkai", "123123");
        result.setData(user).setCode(200).setTotalCount(1);
        return result;
    }
}
