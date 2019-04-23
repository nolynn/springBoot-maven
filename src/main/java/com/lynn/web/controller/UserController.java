package com.lynn.web.controller;

import com.lynn.web.entities.SessionUser;
import com.lynn.web.entities.User;
import com.lynn.web.service.UserService;
import com.lynn.web.utils.BaseResult;
import com.lynn.web.utils.ShiroSessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @Description:
 * @Date: 2019/4/10 14:42
 * @Auther: lynn
 */
@RestController
@Api(tags = "用户模块")
public class UserController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    UserService userService;

    @GetMapping("/user/getUser")
    @RequiresPermissions("get")
    public BaseResult<User> getUser() {
        BaseResult<User> result = new BaseResult<>();
        String username = "zk";
        User user = userService.findUserByUserName(username);
        result.setData(user).setCode(200).setTotalCount(1);
        return result;
    }

    @GetMapping("/unauth")
    public BaseResult unauth() {
        BaseResult result = new BaseResult<>();
        result.setMsg("请登录,再访问。。。").setCode(200).setTotalCount(1);
        return result;
    }

    @PostMapping("/login")
    public BaseResult login(@RequestParam String username, @RequestParam String password) {
        BaseResult result = new BaseResult<>();
        Serializable sessionId = "";

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return result.setMsg("用户名或密码不能为空").setCode(200).setTotalCount(1);
        }

        Subject subject = SecurityUtils.getSubject();
        String msg = "";
        try {
            subject.login(new UsernamePasswordToken(username, password));
        } catch (DisabledAccountException e) {
            msg = "该用户已被禁用，请与管理员联系！";
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误.";
        } catch (UnknownAccountException e) {
            msg = "帐号不存在";
        } catch (AccountException e) {
            msg = "认证失败, 未知原因";
        }
        if (StringUtils.isEmpty(msg) && subject.isAuthenticated()) {
            User user = (User) subject.getPrincipal();
            ShiroSessionUtils.addUserToSession(user);
            sessionId = ShiroSessionUtils.getSession().getId();
            result.setData("用户" + username + "登录成功! sessionId : [" + sessionId + "");
            result.setCode(200);
            logger.info("login success [{}] session_id [{}]", username, sessionId);
            return result;
        }
        result.setMsg(msg);
        return result;
    }

    @ApiOperation(value = "用户登出", notes = "用户登出")
    @PostMapping(value = "/logOut")
    public BaseResult<String> logOut(HttpServletRequest request) {
        BaseResult result = new BaseResult();
        SessionUser sessionUser = ShiroSessionUtils.getSessionUser();
        result.setData("用户" + sessionUser.getUsername() + "退出系统！");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        result.setCode(200);
        result.setData("退出成功！");
        System.out.println("退出成功！");
        return result;
    }

}
