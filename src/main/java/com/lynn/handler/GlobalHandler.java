package com.lynn.handler;

import com.lynn.web.entities.Menu;
import com.lynn.web.service.MenuService;
import com.lynn.web.utils.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @Date: 2019/4/15 15:57
 * @Auther: lynn
 */
@ControllerAdvice
public class GlobalHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalHandler.class);

    /**
     * 处理所有接口数据验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    BaseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.error(e.getMessage(), e);

        BaseResult response = new BaseResult();

        StringBuilder sb = new StringBuilder();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();

        for (ObjectError error : errors) {
            FieldError fieldError = (FieldError) error;
            sb = sb.append(fieldError.getDefaultMessage());
        }
        response.setCode(200);
        response.setMsg(sb.toString());
        return response;
    }

    @Autowired
    MenuService menuService;

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    BaseResult handleUnauthorizedException(UnauthorizedException e) {
        LOGGER.error(e.getMessage(), e);
        String error = e.getMessage();
        String permissionStr = null;
        if (StringUtils.isNotEmpty(error)) {
            permissionStr = error.substring(error.indexOf('[') + 1, error.indexOf(']'));
        }
        BaseResult result = new BaseResult();
        Menu menu = menuService.getMenuByPermission(permissionStr);
        if (null == menu) {
            result.setCode(200).setMsg("权限菜单不存在,配置权限后访问！").setTotalCount(1);
        }
        return result;
    }

}
