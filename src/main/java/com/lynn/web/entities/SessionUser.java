package com.lynn.web.entities;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Date: 2019/4/23 15:04
 * @Auther: lynn
 */
@Data
public class SessionUser {
    private String id;
    private String username;
    private String realName;
    private String permissionStrs;
    private List<String> permissionList;
    private String loginName;
    private String personId;
    private String delFlag;
    private String age;
}
